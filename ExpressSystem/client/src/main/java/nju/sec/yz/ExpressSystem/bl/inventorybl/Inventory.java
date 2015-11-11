package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIHelper;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * 库存的领域模型对象
 * @author 周聪
 *
 */
public class Inventory {
	
	private InventoryDataService data;
	
	public Inventory() {
		try {
			data=DatafactoryProxy.getInventoryDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 库存查看
	 * 设定一个时间段，查看此时间段内的出/入库数量/金额/存储位置
	 * 库存数量要有合计
	 */
	public InventoryVO observeStock(String begin, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public InventoryVO checkStock() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @author cong
	 * 设置库存警报比例
	 * @param rate 库存警报比例，为0-1的double值
	 */
	public ResultMessage setAlertRate(double rate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void exportToExcel() {
		// TODO Auto-generated method stub
		
	}

	public ResultMessage updateInReceipt(InventoryInSheetPO inPO) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
