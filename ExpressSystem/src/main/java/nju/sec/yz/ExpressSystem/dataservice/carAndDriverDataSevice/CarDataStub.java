package nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CarPO;

class CarDataStub {
	public ResultMessage insert(CarPO cpo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	public CarPO find(String id) throws RemoteException{
		return new CarPO("025000B000","苏A203HJ",5);
	}

	public ResultMessage delete(String id) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	public ResultMessage update(CarPO cpo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	public ResultMessage init() throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	public ArrayList<CarPO> findAll() throws RemoteException{
		ArrayList<CarPO> array=new ArrayList<CarPO>();
		array.add(new CarPO("025000B000","苏A203HJ",5));
		array.add(new CarPO("025000B000","苏A20F8K",6));
		return array;
	}
}
