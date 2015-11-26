package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

/**
 * 飞机装运管理
 * @author 周聪
 */
public class TransitFlightReceipt implements ReceiptService{

	@Override
	public ResultMessage make(ReceiptVO vo) {
		
		return null;
	}
	
	/**
	 * 生成航运编号
	 */
	private String createTransportID(String transitId){
		ReceiptID id=new ReceiptID();
		String transportID=id.getID(transitId, IdType.TRANSIT_FLIGHT_TRANSPORT);
		return transportID;
	}
	
	/**
	 * 假设100个包裹为1吨
	 */
	private double cost(int num){
		double weight=num/100.0;
		double distance=CityConst.DISTANCE_OF_POSITION;
		PriceService priceService=new Price();
		double price=priceService.getCarPrice();
		double cost=weight*distance*price;
		return cost;
	}
	
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		TransitSheetVO receipt=(TransitSheetVO)vo;
		TransitReceipt helper=new TransitReceipt();
		ResultMessage validResult=helper.isValid(receipt.getTransitInformation());
		if(validResult.getResult()==Result.FAIL)
			return validResult;
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		
		return null;
	}

}
