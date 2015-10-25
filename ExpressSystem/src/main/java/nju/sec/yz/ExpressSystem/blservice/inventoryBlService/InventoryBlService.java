package nju.sec.yz.ExpressSystem.blservice.inventoryBlService;

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
	public ResultMessage in(ReceiptVO);
	//出库
	public ResultMessage out(ReceiptVO);
}
