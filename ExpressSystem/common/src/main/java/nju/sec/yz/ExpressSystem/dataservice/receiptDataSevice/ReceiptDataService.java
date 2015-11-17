package nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface ReceiptDataService extends Remote{

	public ResultMessage insert(ReceiptPO rpo) throws RemoteException;
	public ReceiptPO find(String id) throws RemoteException;
	public ResultMessage update(ReceiptPO rpo) throws RemoteException;
	public ArrayList<ReceiptPO> findByType(ReceiptType type) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public List<ReceiptPO> findAll() throws RemoteException;
}
