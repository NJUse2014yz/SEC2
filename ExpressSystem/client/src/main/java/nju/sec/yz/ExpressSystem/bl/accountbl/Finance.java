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
	 * 经营情况表
	 * 可以选择开始日期和结束日期
	 * 显示期间内所有的入款单和收款单信息
	 * @param begin 开始日期
	 * @param end	结束日期
	 * @return
	 */
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		BussinessVO vo=new BussinessVO();
		
		Collection in=new Collection();
		vo.in=in.getByTime(begin, end);
		
		Payment out=new Payment();
		vo.out=out.getByTime(begin, end);
		
		return vo;
	}
	
	
	/**
	 * 生成截止当前日期的成本收益表
	 *（总收入、总支出、总利润=总收入-总支出）
	 * @return
	 */
	public ProfitVO makeCostReceipt() {
		Collection in=new Collection();
		double colletion=in.getAllCollection();
		
		Payment out=new Payment();
		double cost=out.getAllPayment();
		
		ProfitVO vo=new ProfitVO(colletion, cost, colletion-cost);
		
		
		return vo;
	}
	
	/**
	 * 导出成本收益表excel
	 * @param rv
	 */
	public void exportCostToExcel(ProfitVO rv) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 导出经营情况表
	 */
	public void exportBussinessToExcel(BussinessVO vo){
		
	}
	
	
}
