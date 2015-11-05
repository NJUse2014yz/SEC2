package nju.sec.yz.ExpressSystem.bl.receiptbl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 单据保存接口
 * @author 周聪
 *
 */
public interface ReceiptSaveService {

	/**
	 * 制定单据完成后通过此方法保存
	 */
	public ResultMessage saveReceipt(ReceiptPO po);
	
	
}
