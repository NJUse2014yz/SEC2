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
		return ResultMessage.SUCCESS;
	}

	@Override
	public CarPO find(String id) throws RemoteException{
		return new CarPO("025000B000","苏A203HJ",5);
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(CarPO cpo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage init() throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public ArrayList<CarPO> findAll() throws RemoteException{
		ArrayList<CarPO> array=new ArrayList<CarPO>();
		array.add(new CarPO("025000B000","苏A203HJ",5));
		array.add(new CarPO("025000B000","苏A20F8K",6));
		return array;
	}
}
