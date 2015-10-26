package nju.sec.yz.ExpressSystem.bl.stub;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class FinanceBlStub implements FinanceBlSevice {

	@Override
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		// TODO 自动生成的方法存根
		
		BussinessVO bussinessVO=new BussinessVO();
		bussinessVO.addInVO(new PaymentSheetVO());
		return bussinessVO;
	}

	@Override
	public ResultMessage checkReceipt(PaymentSheetVO rv, String day,
			int positionId) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage makePayment(OutVO pro) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ProfitVO makeCostReceipt() {
		// TODO 自动生成的方法存根
		return new ProfitVO(1000, 110, 890);
	}

	@Override
	public void exportCostToExcel(ReceiptVO rv) {
		// TODO 自动生成的方法存根
		System.out.println("导出Excel文件成功");

	}

	@Override
	public InitialVO observeIni() {
		// TODO 自动生成的方法存根
		return new InitialVO(null, null, null, null, null);
	}

	@Override
	public ResultMessage initial(AccountVO av) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
