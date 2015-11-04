package nju.sec.yz.ExpressSystem.bl.receiptbl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 保存单据接口
 * @author 周聪
 */
public interface ReceiptService {
	/**
	 * 单据制定完成后通过此方法保存到数据库
	 */
	public ResultMessage saveReceipt(ReceiptPO po);
}
