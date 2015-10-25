package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.AccountPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface AccountDataService {
	public ResultMessage insert(AccountPO apo) throws RemoteException;
	public AccountPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(AccountPO apo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<AccountPO> findAll( ) throws RemoteException;
}
