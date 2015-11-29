package nju.sec.yz.ExpressSystem.data.inventorydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryInDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;

public class InventoryInDataImpl extends UnicastRemoteObject implements InventoryInDataService{

	public InventoryInDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(InventoryInSheetPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(InventoryInSheetPO ipo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryInSheetPO> findByTime(String transit, String timeIn, String timeOut) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
