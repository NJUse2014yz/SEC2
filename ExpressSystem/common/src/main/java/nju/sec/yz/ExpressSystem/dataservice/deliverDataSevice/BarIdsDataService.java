package nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;

public interface BarIdsDataService extends Remote{

	public void add(BarIdsPO po) throws RemoteException;
	
	public void delete(String transitId) throws RemoteException;
	
	public BarIdsPO get(String transitId) throws RemoteException;
	
	public void update(BarIdsPO po) throws RemoteException;
	
}
