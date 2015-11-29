package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;
/**
 * 
 * @author zhangqi
 *
 */
public class InventoryDataStub implements InventoryDataService{
	
	@Override
	public ResultMessage insert(InventoryListPO ipo) throws RemoteException{
		return null;
	}

	@Override
	public ResultMessage update(InventoryListPO ipo) throws RemoteException{
		return null;
	}

	@Override
	public ResultMessage init( ) throws RemoteException{
		return null;
	}

	@Override
	public ArrayList<InventoryListPO> findByTime(String transit,String timeIn,String timeOut)throws RemoteException{
		return new ArrayList<InventoryListPO>();
	}


	@Override
	public ArrayList<InventoryListPO> findAll( ) throws RemoteException{
		return new ArrayList<InventoryListPO>();
	}

	@Override
	public ArrayList<InventoryListPO> findByTime(String transit,String date) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
