package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface AccountBookDataService extends Remote{
	public ArrayList<AccountBookPO> findAll( ) throws RemoteException;
	public AccountBookPO init() throws RemoteException;
	public ResultMessage update(AccountBookPO abp) throws RemoteException;
	public ResultMessage delete(String id)throws RemoteException;
	public ResultMessage insert(AccountBookPO abp)throws RemoteException;
	public AccountBookPO find(String id) throws RemoteException;
	
}
