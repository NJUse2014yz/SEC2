package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import nju.sec.yz.ExpressSystem.ResultMessage;

public interface FinanceBlSevice {
	
	//查看经营情况表
	public BusinessVo checkBusinessCircumstance (String begin,String end);
	//查看收款单
	public ResultMessage checkReceipt(receiveVO rv,String day,int positionId);
	//制定付款单
	public ResultMessage makePayment(payReceiptVO pro);
	//生成成本收益表
	public ResultMessage makeCostReceipt (costReceiptVO crv);
	//导出相应表单的excel文件
	public VOid exportCostToExcel(ReceiptVO rv);
	//查看初期建帐
	public initialVO observeIni();
	//初期建账
	public ResultMessage initial(AccountVO av) ;
}
