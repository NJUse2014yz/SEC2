package nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;
/**
 * 
 * @author zhangqi
 *
 */
public interface ReceipDataService {

	public ResultMessage insert(ReceiptPO rpo) throws RemoteException;
	public ReceiptPO find(String id) throws RemoteException;
	public ResultMessage update(ReceiptPO rpo) throws RemoteException;
	ArrayList<ReceiptPO> findByType(String type) throws RemoteException;
}
