package nju.sec.yz.ExpressSystem.bl.accountbl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 付款单的领域模型对象
 * @author 周聪
 *
 */
public class Payment implements ReceiptService{

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		return null;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		return null;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		return null;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		return null;
	}
}
