package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface InDataService {
	public ResultMessage insert(InPO inpo) throws RemoteException;
	public InPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(InPO inpo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<InPO> findAll( ) throws RemoteException;
}
