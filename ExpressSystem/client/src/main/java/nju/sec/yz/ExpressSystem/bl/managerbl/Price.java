package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.DeliveryType;
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

	public PriceVO observePrize() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrice(DeliveryType type) {
		
		return 20;
	}
	
	
}
