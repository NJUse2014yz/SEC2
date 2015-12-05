package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;
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

public class FinanceBlStub implements FinanceBlSevice {

	@Override
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		// TODO 自动生成的方法存根
		
		BussinessVO bussinessVO=new BussinessVO();
		
		return bussinessVO;
	}

	

	@Override
	public ResultMessage makePayment(OutVO pro) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ProfitVO makeCostReceipt() {
		// TODO 自动生成的方法存根
		return new ProfitVO(1000, 110, 890);
	}


	

	@Override
	public ResultMessage makeReceipt(PaymentSheetVO psv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentSheetVO> checkReceipt(String day, String positionId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ResultMessage exportCostToExcel(ProfitVO vo) {
		return null;
		// TODO Auto-generated method stub
		
	}



	@Override
	public ResultMessage exportBussinessToExcel(BussinessVO vo) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
