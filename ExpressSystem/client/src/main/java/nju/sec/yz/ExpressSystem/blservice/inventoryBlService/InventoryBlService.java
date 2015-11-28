package nju.sec.yz.ExpressSystem.blservice.inventoryBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * @author xiaosaisai
 * 库存管理
 */
public interface InventoryBlService {
	//库存查看
	public ArrayList<InventoryVO> observeStock(String begin,String end);
	//库存盘点
	public ArrayList<InventoryVO> checkStock();
	//导出excel
	public ResultMessage exportToExcel();
	//入库
	public ResultMessage in(InventoryInSheetVO iisvo);
	//出库
	public ResultMessage out(InventoryOutSheetVO iosvo);
	/**
	 * @author cong
	 * 设置库存警报比例
	 * @param rate 库存警报比例，为0-1的double值
	 */
	public ResultMessage setAlertRate(double rate);
}
