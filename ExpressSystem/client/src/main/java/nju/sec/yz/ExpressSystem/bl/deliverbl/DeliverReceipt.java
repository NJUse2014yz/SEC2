package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityDistanceService;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
/**
 * 寄件单的领域模型
 * @author 周聪
 */
public class DeliverReceipt implements ReceiptService{
	@Override
	/**
	 * 检验输入信息
	 * 生成PO
	 */
	public ResultMessage make(ReceiptVO vo) {
		SendSheetVO sendReceipt=(SendSheetVO)vo;
		SendInformation information=sendReceipt.getSendInformation();
		
		//验证information
		String validresult=isValid(information);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
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
		
		//创建PO交给receipt
		SendSheetPO receipt=new SendSheetPO();

		SendInformation info=copyInfo(information);
		//拿到deliverID
		receipt.setId(createID("hh"));
		System.out.println(receipt.getId());
		receipt.setType(ReceiptType.DELIVER_RECEIPT);
		receipt.setSendInformation(info);

		ReceiptSaveService receiptList=new ReceiptList();
		receiptList.saveReceipt(receipt);
		return new ResultMessage(Result.SUCCESS,allCost+" "+time);
	}


	/**
	 * 生成寄件单id
	 * @param deliverID
	 */
	private String createID(String deliverID) {
		ReceiptID idMaker=new ReceiptID();
		String id=idMaker.getID(deliverID, ReceiptType.DELIVER_RECEIPT);
		return id;
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
		SendSheetVO receipt=(SendSheetVO)vo;
		Deliver deliver=new Deliver();
		SendInformation information=receipt.getSendInformation();
		SendSheetPO po=new SendSheetPO();
		//
		SendInformation saveInformation =this.copyInfo(information) ;
		po.setSendInformation(saveInformation);
		ResultMessage resultMessage=deliver.updateDeliverReceipt(po);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		SendSheetVO receipt=(SendSheetVO)vo;
		SendInformation information=receipt.getSendInformation();
		SendSheetPO po=new SendSheetPO();
		//
		SendInformation saveInformation =this.copyInfo(information) ;
		po.setSendInformation(saveInformation);
		return po;
	}

	
	
	@Override
	public ReceiptVO show(ReceiptPO po) {
		SendSheetPO receipt=(SendSheetPO)po;
		SendInformation info=receipt.getSendInformation();
		return null;
	}
	
	private String isValid(SendInformation sif){
		//验证information
		String barId=sif.getBarId();
		String toCellphone=sif.getToPerson().getCellphone();
		String fromCellphone=sif.getFromPerson().getCellphone();
		String total=sif.getGood().getTotal();
		String weight=sif.getGood().getWeight();
		String vloume=sif.getGood().getVloume();
		String size=sif.getGood().getSize();
		
	
		if(!isCellphone(fromCellphone))
			return "亲，不要告诉我寄件人手机号不是11位数字~";
		if(!isCellphone(toCellphone))
			return "亲，不要告诉我收件人手机号不是11位数字~";
		if(!isTotal(total))
			return "亲，件数x是要满足0<x<65536的数字哟";
		if(!isTotal(weight))
			return "亲，重量x是要满足0<x<65536的数字哟";
		if(!isBarId(barId))			
			 return "亲，咱们的订单号是十位数字哟~";
		if(!isTotal(vloume))
			return "亲，体积是要满足0<x<65536的数字哟";
		if(!isSize(size))
			return "亲，尺寸可是要满足“数*数*数”的格式哟";
		return "success";
	}

	

	private boolean isNumber(String str){
		if(str==null||str.length()==0)
			return false;
		char[] numbers=str.toCharArray();
		for(int i=0;i<numbers.length;i++)
			if('0'>numbers[i]||numbers[i]>'9')
				return false;
		return true;
	}
	
	private boolean isBarId(String str){
		if(str==null||str.length()==0)
			return false;
		if(str.length()!=10)
			return false;
		return isNumber(str);
	}
	
	private boolean isCellphone(String str){
		if(str==null||str.length()==0)
			return false;
		if(str.length()!=11)
			return false;
		return isNumber(str);
	}
	
	private boolean isTotal(String str){
		if(str==null||str.length()==0)
			return false;
		if(!isNumber(str))
			return false;
		int n= Integer.parseInt(str);
		if(n<0||n>65536)
			return false;
		return true;
	}
	
	private boolean isSize(String str){
		if(str==null||str.length()==0)
			return false;
		if(!str.contains("*")){
			return false;
		}
		int one=str.indexOf("*");
		if(!isNumber(str.substring(0, one))){
			return false;
		}
		str=str.substring(one+1);
		if(!str.contains("*")){
			return false;
		}
		int two=str.indexOf("*");
		if(!isNumber(str.substring(0, two))||!isNumber(str.substring(two+1))){
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
		double baseprice=price.getPrice();
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
		return distance;
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

