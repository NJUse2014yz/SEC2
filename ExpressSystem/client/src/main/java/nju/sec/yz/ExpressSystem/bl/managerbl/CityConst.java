package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

/**
 * 城市距离常量信息的领域模型对象
 * @author 周聪
 *
 */
public class CityConst implements CityDistanceService{
	public ResultMessage modifyCity(CityVO cv) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public CityVO observeCity(String beginPlace, String endPlace) {
		
		return null;
	}

	
	public ResultMessage addCity(CityVO cp) throws RemoteException {
		return null;
	}

	
	public ResultMessage deleteCity(String beginPlace, String endPlace) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistance(String beginPlace, String endPlace) {
		// TODO Auto-generated method stub
		return 1000;
	}
}
