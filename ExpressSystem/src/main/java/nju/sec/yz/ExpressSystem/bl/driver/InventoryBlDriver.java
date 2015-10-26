package nju.sec.yz.ExpressSystem.bl.driver;

import nju.sec.yz.ExpressSystem.bl.stub.InventoryBlStub;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

/**
 * @author xiaosaisai
 * InventoryBlService的驱动
 */
public class InventoryBlDriver {
	public  void drive(InventoryBlService inventoryBlService){
		//1
		inventoryBlService.exportToExcel();
		System.out.println("单据导出成功");
		//2
		InventoryVO ivo=inventoryBlService.checkStock();
		System.out.println(ivo.getInventoryInInformation()+" "+ivo.getInventoryOutInformation());
		//3
		
		ResultMessage message1=inventoryBlService.in(new InventoryInSheetVO());
		if(message1==ResultMessage.SUCCESS)
			System.out.println("添加入库信息成功");
	}		
	public static void main(String[] args) {
		InventoryBlService service=new InventoryBlStub();
		InventoryBlDriver driver=new InventoryBlDriver();
		driver.drive(service);
	}
}
