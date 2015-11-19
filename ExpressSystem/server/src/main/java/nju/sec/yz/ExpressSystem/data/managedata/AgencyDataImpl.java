package nju.sec.yz.ExpressSystem.data.managedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;

public class AgencyDataImpl extends UnicastRemoteObject implements AgencyDataService{

	public AgencyDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(AgencyPO agpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(AgencyPO agpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AgencyPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
