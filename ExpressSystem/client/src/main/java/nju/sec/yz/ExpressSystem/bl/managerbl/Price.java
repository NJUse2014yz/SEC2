package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

/**
 * 价格常量信息的领域模型
 * @author 周聪
 *
 */
public class Price implements PriceService{
	public ResultMessage modifyPrice(PriceVO pp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public PriceVO observePrice() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * 快递费
	 */
	public double getDeliverPrice() {
		//todo
		return 23;
	}

	@Override
	public double getCarPrice() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public double getTrainPrice() {
		// TODO Auto-generated method stub
		return 0.2;
	}

	@Override
	public double getPlanePrice() {
		// TODO Auto-generated method stub
		return 20;
	}
	
	
	
	
}
