package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 负责财务情况的具体实现
 * 持有付款单和收款单
 * @author 周聪
 */
public class Finance {
	
	
	/**
	 * 按天、按营业厅查看收款单记录
	 * 包括合计功能
	 * @param day
	 * @param positionId
	 * @return
	 */
	public List<PaymentSheetVO> checkReceipt(String day, int positionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * 经营情况表
	 * 可以选择开始日期和结束日期
	 * 显示期间内所有的入款单和收款单信息
	 * @param begin 开始日期
	 * @param end	结束日期
	 * @return
	 */
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 生成截止当前日期的成本收益表
	 *（总收入、总支出、总利润=总收入-总支出）
	 * @return
	 */
	public ProfitVO makeCostReceipt() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 导出excel
	 * @param rv
	 */
	public void exportCostToExcel(ReceiptVO rv) {
		// TODO Auto-generated method stub
		
	}
}
