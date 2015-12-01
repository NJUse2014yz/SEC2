package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryBlStub implements InventoryBlService {

	@Override
	public ResultMessage exportToExcel() {
		System.out.println("导出Excel文件成功");
		return null;
	}

	@Override
	public ArrayList<InventoryListVO> checkStock() {
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

	@Override
	public ResultMessage setAlertRate(double rate) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<InventoryListVO> observeStock(String transit, String begin, String end) {
		// TODO Auto-generated method stub
		return null;
	}

}
