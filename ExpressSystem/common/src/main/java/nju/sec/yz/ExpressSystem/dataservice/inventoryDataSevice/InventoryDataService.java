package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
/**
 * 
 * @author zhangqi
 *
 */
public interface InventoryDataService extends Remote{

	public ResultMessage insert(InventoryPO ipo) throws RemoteException;
	public ResultMessage update(InventoryPO ipo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<InventoryPO> findByTime(String timeIn,String timeOut)throws RemoteException;
	public ArrayList<InventoryPO> findAll( ) throws RemoteException;
}
