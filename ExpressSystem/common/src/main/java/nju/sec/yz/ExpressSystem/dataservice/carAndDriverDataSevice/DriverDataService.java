package nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.DriverPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface DriverDataService extends Remote{
	public ResultMessage insert(DriverPO dpo) throws RemoteException;

	public DriverPO find(String id) throws RemoteException;

	public ResultMessage delete(String id) throws RemoteException;

	public ResultMessage update(DriverPO dpo) throws RemoteException;

	public ResultMessage init() throws RemoteException;

	public ArrayList<DriverPO> findAll() throws RemoteException;
}
