package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryPO;

public class InventoryMockObject {
	public ResultMessage updateInReceipt(InventoryInSheetPO inPO) {
		InventoryPO po=new InventoryPO();
		po.setInventoryInformation(inPO.getInventoryInInformation());
		System.out.println("updating inventory message");
		System.out.println("barID:"+inPO.getBarId());
		return null;
	}
}
