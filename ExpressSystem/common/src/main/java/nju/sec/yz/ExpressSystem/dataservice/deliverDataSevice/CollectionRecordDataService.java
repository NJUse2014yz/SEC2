package nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.po.CollectionRecordPO;

/**
 * 收款记录
 * @author 周聪
 */
public interface CollectionRecordDataService extends Remote{

	public void addRecord(CollectionRecordPO po) throws RemoteException;
	
	public void deleteRecord(String barId) throws RemoteException;
	
	public List<CollectionRecordPO> getRecords(String positionId) throws RemoteException;
	
}
