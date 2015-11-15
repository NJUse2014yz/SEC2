package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class InventoryInMockObject {

	public ResultMessage approve(ReceiptVO vo){
		System.out.println("handling an approved inventoryinsheet...");
		InventoryInSheetPO in=new InventoryInSheetPO(null, "10000");
		InventoryMockObject inventory=new InventoryMockObject();
		inventory.updateInReceipt(in);
		return null;
	}
	
	
}
