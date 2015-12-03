package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.TransitPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface AgencyDataService extends Remote{
	public ResultMessage insert(TransitPO agpo) throws RemoteException;
	public TransitPO find(String id) throws RemoteException;
	public TransitPO findByName(String name) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(TransitPO agpo) throws RemoteException;
	public ResultMessage init(List<TransitPO> transits) throws RemoteException;
	public ArrayList<TransitPO> findAll() throws RemoteException;
}
