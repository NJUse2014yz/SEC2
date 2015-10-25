package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public interface FinanceBlSevice {
	
	//查看经营情况表
	public BusinessVo checkBusinessCircumstance (String begin,String end);
	//查看收款单
	public ResultMessage checkReceipt(ReceVO rv,String day,int positionId);
	//制定付款单
	public ResultMessage makePayment(PaymentSheetVO pro);
	//生成成本收益表
	public ResultMessage makeCostReceipt (costReceiptVO crv);
	//导出相应表单的excel文件
	public void exportCostToExcel(ReceiptVO rv);
	//查看初期建帐
	public InitialVO observeIni();
	//初期建账
	public ResultMessage initial(AccountVO av) ;
}
