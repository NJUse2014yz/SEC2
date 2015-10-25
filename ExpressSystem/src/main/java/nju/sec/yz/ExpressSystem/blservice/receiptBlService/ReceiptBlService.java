package nju.sec.yz.ExpressSystem.blservice.receiptBlService;

/**
 * @author xiaosaisai
 * 单据接口
 */
public interface ReceiptBlService {
	public ArrayList<ReceiptVO> getAll();
	
	public ReceiptVO getSingle(int i);
	
	public ResultMessage approve(ReceiptVO vo);
	
	public ResultMessage deny(ReceiptVO vo， String reason);
	
	public ArrayList<ReceiptVO> getByType(String type);
	
	public ResultMessage modify(ReceiptVO vo);
	
}
