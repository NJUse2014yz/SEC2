package nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.po.ReceiptCountPO;

public interface ReceiptCounterDataService extends Remote{

	public void add(ReceiptCountPO po) throws RemoteException;
	
	public void update(ReceiptCountPO po) throws RemoteException;
	
	public ReceiptCountPO get(String id,ReceiptType type) throws RemoteException;
	
	
}
