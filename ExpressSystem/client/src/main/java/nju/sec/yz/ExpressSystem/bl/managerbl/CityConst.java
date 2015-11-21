package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityIdVO;
import nju.sec.yz.ExpressSystem.vo.CityVO;


/**
 * 城市距离常量信息的领域模型对象
 * @author 周聪
 *
 */
public class CityConst implements CityDistanceService ,CityListService{
	public ResultMessage modifyCity(CityVO cv) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public CityVO observeCity(String beginPlace, String endPlace) {
		
		return null;
	}

	
	public ResultMessage addCity(CityVO cp) throws RemoteException {
		//保存城市常量
		
		
		//保存城市
		
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

	@Override
	public List<CityIdVO> getCities() {
		
		return null;
	}
}
