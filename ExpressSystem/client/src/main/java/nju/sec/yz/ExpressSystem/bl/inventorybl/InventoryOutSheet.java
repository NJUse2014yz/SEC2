package nju.sec.yz.ExpressSystem.bl.inventorybl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 出库单的领域模型对象
 * @author 周聪
 *
 */
public class InventoryOutSheet implements ReceiptService{

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
