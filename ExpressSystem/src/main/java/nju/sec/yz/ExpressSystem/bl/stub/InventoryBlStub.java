package nju.sec.yz.ExpressSystem.bl.stub;

import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;

public class InventoryBlStub implements InventoryBlService {

	@Override
	public void exportToExcel() {
		// TODO 自动生成的方法存根
	}

	@Override
	public InventoryVO observeStock(String begin, String end) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public InventoryVO checkStock() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage in(InventoryInSheetVO iisvo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage out(InventoryOutSheetVO iosvo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
