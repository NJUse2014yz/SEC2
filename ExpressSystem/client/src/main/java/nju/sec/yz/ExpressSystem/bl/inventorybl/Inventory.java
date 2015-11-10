package nju.sec.yz.ExpressSystem.bl.inventorybl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * 库存的领域模型对象
 * @author 周聪
 *
 */
public class Inventory {
	
	public InventoryVO observeStock(String begin, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	
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
		// TODO 自动生成的方法存根
		return null;
	}
}
