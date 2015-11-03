package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
/**
 * 负责财务进出的逻辑控制
 * @author 周聪
 *
 */
public class FinanceController implements FinanceBlSevice{

	@Override
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentSheetVO> checkReceipt(String day, int positionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage makeReceipt(PaymentSheetVO psv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage makePayment(OutVO pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfitVO makeCostReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exportCostToExcel(ReceiptVO rv) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public InitialVO observeIni() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 期初建账
	 */
	@Override
	public ResultMessage initial(AccountVO av) {
		// TODO Auto-generated method stub
		return null;
	}

}
