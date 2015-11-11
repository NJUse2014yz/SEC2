package nju.sec.yz.ExpressSystem.bl.object_driver;

import nju.sec.yz.ExpressSystem.bl.mock_object.InventoryInMockObject;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;

public class InventoryDriver {

	public void drive(){
		InventoryInSheetVO vo=new InventoryInSheetVO();
		InventoryInMockObject in=new InventoryInMockObject();
		in.approve(vo);
	}
	public static void main(String[] args) {
		new InventoryDriver().drive();
	}
}
