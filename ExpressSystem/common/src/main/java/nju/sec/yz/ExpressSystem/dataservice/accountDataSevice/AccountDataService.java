package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.AccountPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface AccountDataService extends Remote{
	public ResultMessage insert(AccountPO apo) throws RemoteException;
	public AccountPO find(String name) throws RemoteException;
	public ResultMessage delete(String name) throws RemoteException;
	public ResultMessage update(AccountPO apo) throws RemoteException;
	public ResultMessage init(List<AccountPO> po) throws RemoteException;
	public ArrayList<AccountPO> findAll() throws RemoteException;
}
