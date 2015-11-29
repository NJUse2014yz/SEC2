package nju.sec.yz.ExpressSystem.data.inventorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryOutDataService;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;

public class InventoryOutDataImpl extends UnicastRemoteObject implements InventoryOutDataService{

	public InventoryOutDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(InventoryOutSheetPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(InventoryOutSheetPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryOutSheetPO> findByTime(String transit, String timeIn, String timeOut) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
