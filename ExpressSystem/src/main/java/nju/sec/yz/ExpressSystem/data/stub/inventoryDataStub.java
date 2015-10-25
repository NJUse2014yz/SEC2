package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
/**
 * 
 * @author zhangqi
 *
 */
public class InventoryDataStub implements InventoryDataService{
	
	@Override
	public ResultMessage insert(InventoryPO ipo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(InventoryPO ipo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage init( ) throws RemoteException{
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<InventoryPO> findByTime(String timeIn,String timeOut)throws RemoteException{
		return new ArrayList<InventoryPO>();
	}

	@Override
	public ArrayList<InventoryPO> findByPosition(String position) throws RemoteException{
		return new ArrayList<InventoryPO>();
	}

	@Override
	public ArrayList<InventoryPO> findAll( ) throws RemoteException{
		return new ArrayList<InventoryPO>();
	}
}