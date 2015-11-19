package nju.sec.yz.ExpressSystem.data.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;

public class AccountBookDataImpl extends UnicastRemoteObject implements AccountBookDataService{

	public AccountBookDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<AccountBookPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBookPO init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBookPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
