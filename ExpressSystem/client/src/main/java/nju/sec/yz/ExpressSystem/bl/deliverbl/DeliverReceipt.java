package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityDistanceService;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.ObjectDeepCopy;
import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.CityVO;
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
		//暂时是控制台输出，后期会变成界面显示
		//System.out.println(validresult);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//自动计算运费和到达时间
		String fromAddress=information.getFromPerson().getAddress();
		String toAddress=information.getToPerson().getAddress();
		double distance=calculataDistance(fromAddress, toAddress);
		String weight=information.getGood().getWeight();
		DeliveryType type = information.getDeliveryType();
		
		double allCost=calculateCost(distance,weight,type)+information.getCostForPack();
		int time=calculateTime(fromAddress,toAddress);
		information.setCostForAll(allCost);
		information.setPredictTime(time);
		
		//创建PO交给receipt
		SendSheetPO receipt=new SendSheetPO();
		sendReceipt.setId(null);
		sendReceipt.setSendInformation(information);
		ReceiptSaveService receiptList=new ReceiptList();
		receiptList.saveReceipt(receipt);
		return new ResultMessage(Result.SUCCESS);
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
		SendInformation saveInformation = (SendInformation) ObjectDeepCopy.deepCopy(information);
		po.setSendInformation(saveInformation);
		ResultMessage resultMessage=deliver.updateDeliverReceipt(po);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		return null;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
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
		String str="success";
		if(!isBarId(barId))
		//TODO 具体对应界面的显示方法				
			str="亲，咱们的订单号是十位数字哟~";
		if(!isCellphone(toCellphone))
			str="亲，不要告诉我寄件人手机号不是11位数字~";
		if(!isCellphone(fromCellphone))
			str="亲，不要告诉我收件人手机号不是11位数字~";
		if(!isTotal(total))
			str="亲，件数x是要满足0<x<65536的数字哟";
		if(!isTotal(weight))
			str="亲，重量x是要满足0<x<65536的数字哟";
		if(!isTotal(vloume))
			str="亲，体积x是要满足0<x<65536的数字哟";
		if(!isSize(size))
			str="亲，size可是要满足“数*数*数”的格式哟";
		return str;
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
		System.out.println(str);
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
		else if(distance<250){
			return 1;
		}
		else if(distance<500){
			return 2;
		}
		else if(distance<750){
			return 3;
		}
		else if(distance<1000){
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
		System.out.println(receipt.isBarId("1234567890"));
		System.out.println(receipt.isCellphone("1344577895"));
		System.out.println(receipt.isNumber(" 1"));
		System.out.println(receipt.isSize("1*2*34"));
		System.out.println(receipt.isTotal("34234566"));
	}
*/

}

