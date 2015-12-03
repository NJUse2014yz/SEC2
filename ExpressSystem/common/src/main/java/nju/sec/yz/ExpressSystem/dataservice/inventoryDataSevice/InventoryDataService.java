package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
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
	 * 根据中转中心和条形码号确定
	 */
	public ResultMessage delete(String transitId,String barId) throws RemoteException;
	
	
	public ResultMessage init(List<InventoryInSheetPO> inventories) throws RemoteException;
	
	
	/**
	 * 查看当天仓库中还未出库的库存
	 * @param date 今天的日期
	 */
	public List<InventoryInSheetPO> findAll(String transit)throws RemoteException;
}
