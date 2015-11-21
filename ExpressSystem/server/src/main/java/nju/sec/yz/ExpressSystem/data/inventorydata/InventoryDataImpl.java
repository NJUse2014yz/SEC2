package nju.sec.yz.ExpressSystem.data.inventorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryPO;

public class InventoryDataImpl extends UnicastRemoteObject implements InventoryDataService{

	public InventoryDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(InventoryPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(InventoryPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InventoryPO> findByTime(String timeIn, String timeOut) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public ArrayList<InventoryPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
