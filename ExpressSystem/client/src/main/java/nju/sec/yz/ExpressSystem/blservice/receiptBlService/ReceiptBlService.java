package nju.sec.yz.ExpressSystem.blservice.receiptBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * @author xiaosaisai
 * 单据接口
 */
public interface ReceiptBlService {
	public ArrayList<ReceiptVO> getAll();
	
	public ReceiptVO getSingle(String i);
	
	public ResultMessage approve(ReceiptVO vo);
	
	public ArrayList<ReceiptVO> getByType(ReceiptType type);
	
	public ResultMessage modify(ReceiptVO vo);
	
}
