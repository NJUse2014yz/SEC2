package nju.sec.yz.ExpressSystem.blservice.inventoryBlService;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryPO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;

/**
 * @author xiaosaisai
 * 库存管理
 */
public interface InventoryBlService {
	//库存查看
	public InventoryVO observeStock(String begin,String end);
	//库存盘点
	public InventoryVO checkStock();
	//导出excel
	public void exportToExcel();
	//入库
	public ResultMessage in(InventoryInSheetVO iisvo);
	//出库
	public ResultMessage out(InventoryOutSheetVO iosvo);
}
