package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.OutPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface OutDataService extends Remote{
	public ResultMessage insert(OutPO outpo) throws RemoteException;
	public OutPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(OutPO outpo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<OutPO> findAll( ) throws RemoteException;
}
