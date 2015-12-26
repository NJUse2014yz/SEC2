package nju.sec.yz.ExpressSystem.blservice.receiptBlService;

import java.util.List;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * @author xiaosaisai
 * 单据接口
 */
public interface ReceiptBlService {
	/**
	 * 获得所有表单数据
	 */
	public List<ReceiptVO> getAll();
	
	/**
	 * 获得某类型的所有单据
	 * @param type
	 * @return
	 */
	public List<ReceiptVO> getByType(ReceiptType type);
	
	/**
	 * 通过id获得表单数据
	 * @param i 单据id
	 */
	public ReceiptVO getSingle(String i);
	
	/**
	 * 审批单据
	 * @param vo
	 * @return
	 */
	public ResultMessage approve(ReceiptVO vo);
	
	/**
	 * 修改单据信息
	 * @param vo
	 * @return
	 */
	public ResultMessage modify(ReceiptVO vo);
	
}
