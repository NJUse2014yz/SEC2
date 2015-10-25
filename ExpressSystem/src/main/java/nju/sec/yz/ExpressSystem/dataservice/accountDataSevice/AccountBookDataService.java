package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;

/**
 * 
 * @author zhangqi
 *
 */
public interface AccountBookDataService {
	public ArrayList<AccoutBookPO> findAll( ) throws RemoteException;
	public AccountBookPO init() throws RemoteException;
	public ResultMessage update(AccountBookPO abp) throws RemoteException;
	public ResultMessage delete(AccountBookPO abp)throws RemoteException;
	public ResultMessage insert(AccountBookPO abp)throws RemoteException;
	public AccountBookPO find(String id) throws RemoteException;
	
}
