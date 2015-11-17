package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

/**
 * 司机信息的领域模型对象
 * @author 周聪
 *
 */
public class Driver {
	
	private DriverDataService data;
	
	public Driver() {
		try {
			data=DatafactoryProxy.getDriverDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<DriverVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public DriverVO getSingle(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage add(DriverVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modify(DriverVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
