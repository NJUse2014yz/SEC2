package nju.sec.yz.ExpressSystem.bl.receiptbl;

import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 所有单据的通用接口
 * @author 周聪
 */
public interface  ReceiptService {
	/**
	 * 制定单据
	 */
	public ResultMessage make(ReceiptVO vo);
	
	/**
	 * 单据审批之后通过此方法更新信息到相应数据库
	 */
	public ResultMessage approve(ReceiptVO vo);
	
	/**
	 * 将VO转换成PO
	 */
	public ReceiptPO convertToPO(ReceiptVO vo);
	
	/**
	 * 验证单据信息
	 */
	public ResultMessage isValid(ReceiptVO vo);
	
	/**
	 * 将PO转换成VO
	 */
	public ReceiptVO show(ReceiptPO po);
	
	/**
	 * 获得消息
	 */
	public String showMessage(ReceiptVO vo);
}
