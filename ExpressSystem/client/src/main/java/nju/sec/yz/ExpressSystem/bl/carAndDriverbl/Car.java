package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * 汽车信息的领域模型对象
 * @author 周聪
 *
 */
public class Car {
	
	private CarDataService carData;
	
	public Car() {
		try {
			carData=DatafactoryProxy.getCarDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<CarVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CarVO getSingle(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage add(CarVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modify(CarVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
