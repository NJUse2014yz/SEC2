package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityDistanceService;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.po.CollectionRecordPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
/**
 * 寄件单的领域模型
 * @author 周聪
 */
public class DeliverReceipt implements ReceiptService{
	
	private OrderDataService orderData;
	
	public DeliverReceipt() {
		try {
			orderData=DatafactoryProxy.getOrderDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * 检验输入信息
	 * 生成PO
	 */
	public ResultMessage make(ReceiptVO vo) {
		SendSheetVO sendReceipt=(SendSheetVO)vo;
		SendInformation information=sendReceipt.getSendInformation();
		
		//验证information
		ResultMessage validresult=isValid(sendReceipt);
		if(validresult.getResult()==Result.FAIL)
			return validresult;
		
		Deliver deliver=new Deliver();
		if(deliver.checkDeliver(information.getBarId())!=null)
			return new ResultMessage(Result.FAIL,"亲，这订单号已经填过了哦~");
		
		//自动计算运费和到达时间
		String fromCity=information.getFromPerson().getCity();
		String toCity=information.getToPerson().getCity();
		double distance=calculataDistance(fromCity, toCity);
		String weight=information.getGood().getWeight();
		DeliveryType type = information.getDeliveryType();

		PackType packType=information.getPackType();
		information.setCostForPack(packType.getPrice());
		double allCost=calculateCost(distance,weight,type)+information.getCostForPack();
		int time=calculateTime(fromCity,toCity);
		information.setCostForAll(allCost);
		System.out.println(allCost);
		information.setPredictTime(time);
		
		
		//创建PO交给receiptList
		SendSheetPO receipt=new SendSheetPO();

		SendInformation info=copyInfo(information);
		receipt.setId(createID());
		receipt.setType(ReceiptType.DELIVER_RECEIPT);
		receipt.setMakePerson(this.getDeliverID());
		receipt.setMakeTime(TimeTool.getDate());
		receipt.setSendInformation(info);

		ReceiptSaveService receiptList=new ReceiptList();
		ResultMessage saveResult=receiptList.saveReceipt(receipt);
		if(saveResult.getResult()==Result.FAIL)
			return saveResult;
		
		//更新物流信息
		
		deliver.newDeliverInfo(info.getBarId(), "快递员正在揽件中... "+TimeTool.getDate());
		
		//保存收款记录(暂定审批前保存)
		String deliverId=receipt.getMakePerson();
		String positionId=deliverId.split("D")[0];
		CollectionRecordPO po=new CollectionRecordPO(info.getBarId(), receipt.getMakeTime(),
												allCost,deliverId, positionId);
		CollectionRecord record=new CollectionRecord();
		record.addRecord(po);
		
				
		return new ResultMessage(Result.SUCCESS,allCost+" "+time);
	}


	/**
	 * 生成寄件单id
	 */
	private String createID() {
		String deliverID=this.getDeliverID();
		ReceiptID idMaker=new ReceiptID();
		String id=idMaker.getID(deliverID, IdType.DELIVER_RECEIPT);
		return id;
	}
	
	/**
	 * 得到填单人的id
	 */
	private String getDeliverID(){
		UserInfo user=new User();
		String id=user.getCurrentID();
		return id;
	}
	
	
	/**
	 * 从数据层获得订单信息
	 */
	public SendSheetVO getOrder(String barID){
		SendSheetPO po=null;
		
		try {
			po=orderData.get(barID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
		
		if(po==null)
			return null;
		
		SendSheetVO vo=new SendSheetVO();
		SendInformation info=copyInfo(po.getSendInformation());
		vo.setSendInformation(info);
		vo.setId(po.getId());
		vo.setType(po.getType());
		
		return vo;
	}
	
	private ResultMessage saveOrder(SendSheetPO po){
		ResultMessage message=null;
		try {
			message=orderData.add(po);
		} catch (RemoteException e) {
			
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "网络异常");
		}
		return message;
	}
	
	/**
	 * 复制info的所有数据
	 */
	private SendInformation copyInfo(SendInformation info){
		ToAndFromInformation to = info.getToPerson();
		ToAndFromInformation from = info.getFromPerson();
		GoodInformation good = info.getGood();

		ToAndFromInformation toPerson = new ToAndFromInformation(to.getName(), to.getCity(), to.getAddress(),
				to.getOrg(), to.getTelephone(), to.getCellphone());

		ToAndFromInformation fromPerson = new ToAndFromInformation(from.getName(), from.getCity(), from.getAddress(),
				from.getOrg(), from.getTelephone(), from.getCellphone());
		GoodInformation goodInfo = new GoodInformation(good.getTotal(), good.getWeight(), good.getVloume(),
				good.getName(), good.getSize());

		SendInformation information = new SendInformation(info.getBarId(), toPerson, fromPerson, goodInfo,
				info.getDeliveryType(), info.getPackType());

		information.setCostForAll(info.getCostForAll());
		information.setCostForPack(info.getCostForPack());
		information.setPredictTime(info.getPredictTime());

		return information;
	}
	
	

	@Override
	/**
	 * 审批完成后更新信息
	 */
	public ResultMessage approve(ReceiptVO vo) {
		
		//保存订单信息
		SendSheetVO receipt=(SendSheetVO)vo;
		SendInformation info=receipt.getSendInformation();
		SendSheetPO po=new SendSheetPO();
		SendInformation saveInformation =this.copyInfo(info) ;
		po.setSendInformation(saveInformation);
		this.saveOrder(po);
		
		//更新物流信息
		Deliver deliver=new Deliver();
		String nextAgency=vo.getMakePerson().split("D")[0];
		String trail="快递员已揽件，预计" + info.getPredictTime() + "天送达" + " " + vo.getMakeTime();
		ResultMessage resultMessage = deliver.updateDeliverInfo(info.getBarId(), trail, DeliveryState.GATHER,
				nextAgency);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		SendSheetVO receipt=(SendSheetVO)vo;
		SendInformation information=receipt.getSendInformation();
		SendSheetPO po=new SendSheetPO();
		SendInformation saveInformation =this.copyInfo(information);
		po.setSendInformation(saveInformation);
		po.setId(receipt.getId());
		po.setType(ReceiptType.DELIVER_RECEIPT);
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		return po;
	}

	
	
	@Override
	public ReceiptVO show(ReceiptPO po) {
		SendSheetPO receipt=(SendSheetPO)po;
		SendInformation info=receipt.getSendInformation();
		SendSheetVO vo=new SendSheetVO();
		vo.setSendInformation(info);
		vo.setId(po.getId());
		vo.setType(ReceiptType.DELIVER_RECEIPT);
		vo.setMakePerson(po.getMakePerson());
		vo.setMakeTime(po.getMakeTime());
		return vo;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo){
		SendInformation sif=((SendSheetVO)vo).getSendInformation();
		//验证information
		String barId=sif.getBarId();
		String toCellphone=sif.getToPerson().getCellphone();
		String fromCellphone=sif.getFromPerson().getCellphone();
		String total=sif.getGood().getTotal();
		String weight=sif.getGood().getWeight();
		String vloume=sif.getGood().getVloume();
		String size=sif.getGood().getSize();
		
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		if(!ValidHelper.isCellphone(fromCellphone))
			message.setMessage("亲，不要告诉我寄件人手机号不是11位数字~");
		else if(!ValidHelper.isCellphone(toCellphone))
			message.setMessage("亲，不要告诉我收件人手机号不是11位数字~");
		else if(!ValidHelper.isValidNumber(total))
			message.setMessage("亲，件数x是要满足0<x<65536的数字哟");
		else if(!ValidHelper.isValidNumber(weight))
			message.setMessage("亲，重量x是要满足0<x<65536的数字哟");
		else if(!ValidHelper.isBarId(barId))			
			message.setMessage("亲，咱们的订单号是十位数字哟~");
		
		
		
		
		else if(!ValidHelper.isValidNumber(vloume))
			message.setMessage("亲，体积是要满足0<x<65536的数字哟");
		else if(!isSize(size))
			message.setMessage("亲，尺寸可是要满足“数*数*数”的格式哟");
		else 
			message.setResult(Result.SUCCESS);
		return message;
	}

	private boolean isSize(String str){
		if(str==null||str.length()==0)
			return false;
		if(!str.contains("*")){
			return false;
		}
		int one=str.indexOf("*");
		if(!ValidHelper.isNumber(str.substring(0, one))){
			return false;
		}
		str=str.substring(one+1);
		if(!str.contains("*")){
			return false;
		}
		int two=str.indexOf("*");
		if(!ValidHelper.isNumber(str.substring(0, two))||!ValidHelper.isNumber(str.substring(two+1))){
			return false;
		}
		return true;
	}	
	
	private double calculateCost(double distance, String weight, DeliveryType type){
		double cost=0.0;
		double weight1=Double.parseDouble(weight);
		double rate=1.0;
		switch(type){
		case ECONOMIC:rate=18.0/23;break;
		case FAST:rate=25.0/23;break;
		case STANDARD:rate=1.0;break;
		default:
			break;
		}
		PriceService price=new Price();
		double baseprice=price.getDeliverPrice();
		cost=distance/1000*rate*weight1*baseprice;
		return cost;
	}
	
	private int calculateTime(String from, String to) {
		double distance=calculataDistance(from, to);
		//以250km作为一天时间分割线
		if(distance<0){
			System.out.println("wrong distance");
			return -1;
		}
		else if(distance<=250){
			return 1;
		}
		else if(distance<=500){
			return 2;
		}
		else if(distance<=750){
			return 3;
		}
		else if(distance<=1000){
			return 4;
		}
		else 
			return 5;
	}
	
	/**
	 * 从CityConstBl中获得城市距离常量
	 */
	private double calculataDistance(String from, String to){
		CityDistanceService cities=new CityConst();
		double distance=cities.getDistance(from, to);
		System.out.println("distance="+distance);
		return distance;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		SendSheetVO sendReceipt=(SendSheetVO)vo;
		SendInformation info=sendReceipt.getSendInformation();
		String message="条形码号："+info.getBarId()+StringTool.nextLine();
		message=message+"寄件人："+info.getFromPerson().getName()+StringTool.nextLine();
		message=message+"收件人："+info.getToPerson().getName()+StringTool.nextLine();
		
		return message;
	}


/*	public static void main(String[] args) {
		DeliverReceipt receipt=new DeliverReceipt();
		System.out.println(receipt.calculateCost(900, "1", DeliveryType.FAST));
		System.out.println(receipt.isBarId("1234567890"));
		System.out.println(receipt.isCellphone("1344577895"));
		System.out.println(receipt.isNumber(" 1"));
		System.out.println(receipt.isSize("1*2*34"));
		System.out.println(receipt.isTotal("34234566"));
	}

*/
}

