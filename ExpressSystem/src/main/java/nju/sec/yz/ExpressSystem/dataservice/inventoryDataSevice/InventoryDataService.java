package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;
/**
 * 
 * @author zhangqi
 *
 */
public interface InventoryDataService {

	public ResultMessage insert(InventoryPO ipo) throws RemoteException;
	public ResultMessage update(InventoryPO ipo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	public ArrayList<InventoryPO> findByTime(String timeIn,String timeOut)throws RemoteException;
	public ArrayList<InventoryPO> findByPosition(String position) throws RemoteException;
	public ArrayList<InventoryPO> findAll( ) throws RemoteException;
}
