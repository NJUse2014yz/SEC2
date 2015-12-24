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
	/**
	 * 经营情况表
	 * 可以选择开始日期和结束日期
	 * 显示期间内所有的入款单和收款单信息
	 * @param begin 开始日期
	 * @param end	结束日期
	 * @return
	 */
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
	
	/**
	 * 制定付款单
	 * @param pro
	 * @return
	 */
	public ResultMessage makePayment(OutVO pro);
	
	/**
	 * 生成截止当前日期的成本收益表
	 *（总收入、总支出、总利润=总收入-总支出）
	 * @return
	 */
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
