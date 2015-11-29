package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;

/**
 * 入库单数据
 * @author 周聪
 *
 */
public interface InventoryInDataService extends Remote{
	public ResultMessage insert(InventoryInSheetPO ipo) throws RemoteException;
	public ResultMessage update(InventoryInSheetPO ipo) throws RemoteException;
	public ResultMessage init( ) throws RemoteException;
	
	/**
	 * 查看某时间段内的入库信息
	 * @param timeIn
	 * @param timeOut
	 */
	public List<InventoryInSheetPO> findByTime(String transit,String timeIn,String timeOut)throws RemoteException;

}
