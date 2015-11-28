package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryVO;
/**
 * 负责实现库存管理界面所需要的服务
 * @author 周聪
 */
public class InventoryController implements InventoryBlService{

	@Override
	public ArrayList<InventoryVO> observeStock(String begin, String end) {
		Inventory inven=new Inventory();
		ArrayList<InventoryVO> voList=inven.observeStock(begin, end);
		return voList;
	}

	@Override
	public ArrayList<InventoryVO> checkStock() {
		Inventory inven=new Inventory();
		ArrayList<InventoryVO> voList=inven.checkStock();
		return voList;
	}

	@Override
	public ResultMessage exportToExcel() {
		Inventory inven=new Inventory();
		ResultMessage  message=inven.exportToExcel();
		return message;
	}

	@Override
	public ResultMessage in(InventoryInSheetVO iisvo) {
		InventoryInSheet receipt=new InventoryInSheet();
		ResultMessage resultMessage=receipt.make(iisvo);
		return resultMessage;
	}

	@Override
	public ResultMessage out(InventoryOutSheetVO iosvo) {
		InventoryOutSheet receipt=new InventoryOutSheet();
		ResultMessage resultMessage=receipt.make(iosvo);
		return resultMessage;
	}

	@Override
	public ResultMessage setAlertRate(double rate) {
		Inventory inven=new Inventory();
		ResultMessage message=inven.setAlertRate(rate);
		return message;
	}

}
