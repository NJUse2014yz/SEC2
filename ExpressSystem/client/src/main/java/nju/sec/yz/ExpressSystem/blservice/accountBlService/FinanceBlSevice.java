package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public interface FinanceBlSevice {
	
	//查看经营情况表
	public BussinessVO checkBusinessCircumstance (String begin,String end);
	/**
	 * 按营业厅查看收款单
	 * @author cong
	 */
	public PaymentVO checkReceipt (String day,String positionId);
	
	
	/**
	 * 制定收款单
	 * @author cong
	 */
	public ResultMessage makeReceipt (PaymentSheetVO psv);
	
	
	//制定付款单
	public ResultMessage makePayment(OutVO pro);
	//生成成本收益表
	public ProfitVO makeCostReceipt ();
	
	/**
	 * 导出成本收益表的excel文件
	 */
	public ResultMessage exportCostToExcel(ProfitVO vo);
	
	/**
	 * 导出经营情况表的excel文件
	 */
	public ResultMessage exportBussinessToExcel(BussinessVO vo);
}
