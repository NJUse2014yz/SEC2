package nju.sec.yz.ExpressSystem.blservice.inventoryBlService;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitOutVO;

/**
 * @author xiaosaisai
 * 库存管理
 */
public interface InventoryBlService {
	/**
	 * 前置条件：中转单出发地需为当前用户中转中心
	 * 输入中转单或汽运编号获得条形码号列表，目的地，运输方式
	 */
	public TransitOutVO getBarIdList(String transitSheetId);
	
	/**
	 * 获得当前中转中心可送达的目的地
	 */
	public List<String> getValidDestination();
	
	//库存查看
	public InventoryListVO observeStock(String begin,String end);
	//库存盘点
	public InventoryListVO checkStock();
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
