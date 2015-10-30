package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.AgencyPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface AgencyDataService {
	public ResultMessage insert(AgencyPO agpo) throws RemoteException;
	public AgencyPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(AgencyPO agpo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<AgencyPO> findAll( ) throws RemoteException;
}
