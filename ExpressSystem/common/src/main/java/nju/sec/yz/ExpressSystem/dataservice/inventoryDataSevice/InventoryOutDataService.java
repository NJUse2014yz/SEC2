package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;

public interface InventoryOutDataService extends Remote{
	public ResultMessage insert(InventoryOutSheetPO ipo) throws RemoteException;
	public ResultMessage update(InventoryOutSheetPO ipo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	
	/**
	 * 查看某时间段内的出库信息
	 * @param timeIn
	 * @param timeOut
	 */
	public List<InventoryOutSheetPO> findByTime(String transit,String timeIn,String timeOut)throws RemoteException;
}
