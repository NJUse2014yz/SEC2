package nju.sec.yz.ExpressSystem.bl.inventorybl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

/**
 * 入库单的领域模型对象
 * @author 周聪
 *
 */
public class InventoryInSheet implements ReceiptService {
	//通过入库单的审批
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		Inventory inventory=new Inventory();
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		InventoryInSheetPO inPO = new InventoryInSheetPO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		ResultMessage resultMessage=inventory.updateInReceipt(inPO);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		//验证information
		
		
		//创建PO交给receipt
		InventoryInSheetPO inPO = new InventoryInSheetPO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		ReceiptList receiptList = new ReceiptList();
		receiptList.saveReceipt(inPO);
		return ResultMessage.SUCCESS;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
	public ResultMessage isValid(InventoryInInformation ii){
		//假定柜子刚好99个
		if(ii.getBlock()==0){
			return ResultMessage.FAIL;
			//增加ResultMessage，返回fail的具体原因
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}
}
