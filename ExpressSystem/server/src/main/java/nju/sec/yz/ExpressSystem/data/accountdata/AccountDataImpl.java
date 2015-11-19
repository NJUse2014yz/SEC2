package nju.sec.yz.ExpressSystem.data.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;

public class AccountDataImpl extends UnicastRemoteObject implements AccountDataService{

	public AccountDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(AccountPO apo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(AccountPO apo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
