package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.po.PricePO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

/**
 * 价格常量信息的领域模型
 * @author 周聪
 * @update sai
 */
public class Price implements PriceService{
	private ConstDataService data;
	public  Price(){
		try {
			data=DatafactoryProxy.getConstDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	public ResultMessage modifyPrice(PriceVO pv) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(pv);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		PricePO po=changePoToVo(pv);
		try{
			message=data.updatePrice(po);
		}catch(RemoteException e){
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	public PriceVO observePrice(){
		PricePO po=null;
		System.out.println("price");
		try{
			po=data.findAllPrice();		
			if(po==null)
				return null;
		}catch(RemoteException e){
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		PriceVO vo=changePoToVo(po);
		return vo;
	}
	
	@Override
	/**
	 * 快递费
	 */
	public double getDeliverPrice() {
		return 23;
//		PriceVO vo=this.observePrice();
//		return vo.getPriceInformation().getStandard();
	}

	@Override
	public double getCarPrice() {
		PriceVO vo=this.observePrice();
		return vo.getPriceInformation().getPriceForCar();
	}

	@Override
	public double getTrainPrice() {
		PriceVO vo=this.observePrice();
		return vo.getPriceInformation().getPriceForTrain();
	}

	@Override
	public double getPlanePrice() {
		PriceVO vo=this.observePrice();
		return vo.getPriceInformation().getPriceForPlane();
	}
	
	private PriceVO changePoToVo(PricePO po) {
		PriceInformation information=po.getPriceInformation();
		PriceVO vo=new PriceVO();
		vo.setPriceInformation(information);
		return vo;
	}
	
	private PricePO changePoToVo(PriceVO pv) {
		PriceInformation information=pv.getPriceInformation();
		PricePO po=new PricePO();
		po.setPriceInformation(information);
		return po;
	}
	
	private String isValid(PriceVO pv) {
		PriceInformation information=pv.getPriceInformation();
		double priceForCar=information.getPriceForCar();
		double priceForTrain=information.getPriceForTrain();
		double priceForPlane=information.getPriceForPlane();
		double standard=information.getStandard();
		
		if(!ValidHelper.isValidDouble(priceForCar))
			return "Wrong Price For Car";
		if(!ValidHelper.isValidDouble(priceForTrain))
			return "Wrong Price For Train";
		if(!ValidHelper.isValidDouble(priceForPlane))
			return "Wrong Price For Plane";
		if(!ValidHelper.isValidDouble(standard))
			return "Wrong Standard";
		return "success";
	}
	
	
}
