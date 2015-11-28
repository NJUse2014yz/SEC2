package nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
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
	public ArrayList<InventoryPO> findAll( ) throws RemoteException;
	
	/**
	 * 查看某时间段内的出入库信息
	 * @param timeIn
	 * @param timeOut
	 */
	public ArrayList<InventoryPO> findByTime(String transit,String timeIn,String timeOut)throws RemoteException;
	/**
	 * 查看当天库存
	 * @param date 今天的日期
	 */
	public ArrayList<InventoryPO> findByTime(String transit,String date)throws RemoteException;
}
