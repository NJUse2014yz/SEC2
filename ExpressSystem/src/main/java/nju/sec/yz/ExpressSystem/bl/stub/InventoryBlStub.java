package nju.sec.yz.ExpressSystem.bl.stub;

import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;

public class InventoryBlStub implements InventoryBlService {

	@Override
	public void exportToExcel() {
		System.out.println("导出Excel文件成功");
	}

	@Override
	public InventoryVO observeStock(String begin, String end) {
		return new InventoryVO();
	}

	@Override
	public InventoryVO checkStock() {
		// TODO 自动生成的方法存根
		return new InventoryVO();
	}

	@Override
	public ResultMessage in(InventoryInSheetVO iisvo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage out(InventoryOutSheetVO iosvo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}