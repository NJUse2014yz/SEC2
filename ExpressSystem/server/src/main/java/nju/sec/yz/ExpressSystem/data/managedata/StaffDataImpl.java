package nju.sec.yz.ExpressSystem.data.managedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.StaffPO;

public class StaffDataImpl extends UnicastRemoteObject implements StaffDataService{

	public StaffDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(StaffPO spo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(StaffPO spo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StaffPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
