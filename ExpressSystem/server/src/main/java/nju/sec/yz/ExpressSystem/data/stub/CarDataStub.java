package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;

/**
 * 
 * @author zhangqi
 *
 */
public class CarDataStub implements CarDataService{
	@Override
	public ResultMessage insert(CarPO cpo) throws RemoteException{
		return null;
	}

	@Override
	public CarPO find(String id) throws RemoteException{
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException{
		return null;
	}

	@Override
	public ResultMessage update(CarPO cpo) throws RemoteException{
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException{
		return null;
	}
	
	@Override
	public ArrayList<CarPO> findAll() throws RemoteException{
		ArrayList<CarPO> array=new ArrayList<CarPO>();
		return array;
	}
}
