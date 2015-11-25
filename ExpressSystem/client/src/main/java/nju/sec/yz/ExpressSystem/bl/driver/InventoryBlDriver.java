package nju.sec.yz.ExpressSystem.bl.driver;

import nju.sec.yz.ExpressSystem.bl.stub.InventoryBlStub;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * @author xiaosaisai InventoryBlService的驱动
 */
public class InventoryBlDriver {
	public void drive(InventoryBlService inventoryBlService) {
		// 1
		inventoryBlService.exportToExcel();
		System.out.println("单据导出成功");
		// 2库存盘点
		InventoryVO ivo1 = inventoryBlService.checkStock();
		System.out.println(ivo1.getInventoryInInformation() + "[没有信息吗]" + ivo1.getInventoryOutInformation());
		// 3库存查看
		
		
		// 4入库
	

		System.out.println("不好意思，操作失败哟");
		// 5出库
		ResultMessage message2 = inventoryBlService.out(new InventoryOutSheetVO());

		System.out.println("不好意思，操作失败哟");
	}
	// public static void main(String[] args) {
	// InventoryBlService service=new InventoryBlStub();
	// InventoryBlDriver driver=new InventoryBlDriver();
	// driver.drive(service);
	// }
}
