package nju.sec.yz.ExpressSystem.bl.inventorybl;

import nju.sec.yz.ExpressSystem.bl.deliverbl.BarIdList;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitOutVO;

/**
 * 负责实现库存管理界面所需要的服务
 * 
 * @author 周聪
 */
public class InventoryController implements InventoryBlService {

	@Override
	/**
	 * 前置条件：中转单出发地需为当前用户中转中心
	 * 输入中转单或汽运编号获得条形码号列表，目的地，运输方式
	 */
	public TransitOutVO getBarIdList(String transitSheetId) {
		// 获得出发地id
		UserInfo user = new User();
		String userId = user.getCurrentID();
		
		//判断是否为仓库管理员
		if(!userId.contains("A"))
			return null;
		
		String transitId=userId.split("A")[0];
		BarIdList list=new BarIdList();
		
		return list.getTransitOutInfo(transitSheetId, transitId);
	}

	@Override
	public InventoryListVO observeStock(String begin, String end) {
		Inventory inven = new Inventory();
		InventoryListVO voList = inven.observeStock(begin, end);
		return voList;
	}

	@Override
	public InventoryListVO checkStock() {
		Inventory inven = new Inventory();
		InventoryListVO voList = inven.checkStock();
		return voList;
	}

	@Override
	public ResultMessage exportToExcel() {
		Inventory inven = new Inventory();
		ResultMessage message = inven.exportToExcel();
		return message;
	}

	@Override
	public ResultMessage in(InventoryInSheetVO iisvo) {
		InventoryInSheet receipt = new InventoryInSheet();
		ResultMessage resultMessage = receipt.make(iisvo);
		return resultMessage;
	}

	@Override
	public ResultMessage out(InventoryOutSheetVO iosvo) {
		InventoryOutSheet receipt = new InventoryOutSheet();
		ResultMessage resultMessage = receipt.make(iosvo);
		return resultMessage;
	}

	@Override
	public ResultMessage setAlertRate(double rate) {
		Inventory inven = new Inventory();
		ResultMessage message = inven.setAlertRate(rate);
		return message;
	}

}
