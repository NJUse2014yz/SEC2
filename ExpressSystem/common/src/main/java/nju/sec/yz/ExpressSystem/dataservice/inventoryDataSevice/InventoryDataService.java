package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;
/**
 * 
 * @author zhangqi
 *
 */
public interface InventoryDataService extends Remote{

	/**
	 * 入库时添加
	 */
	public ResultMessage insert(InventoryInSheetPO ipo) throws RemoteException;
	
	
	/**
	 * 出库后删除
	 */
	public ResultMessage delete(String id) throws RemoteException;
	
	
	public ResultMessage init( ) throws RemoteException;
	
	
	/**
	 * 查看当天仓库中还未出库的库存
	 * @param date 今天的日期
	 */
	public List<InventoryInSheetPO> findAll(String transit)throws RemoteException;
}
