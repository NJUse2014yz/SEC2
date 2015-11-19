package nju.sec.yz.ExpressSystem.data.logdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.po.LogPO;

public class LogDataImpl extends UnicastRemoteObject implements LogDataService{

	public LogDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(LogPO lpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogPO find(String time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LogPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
