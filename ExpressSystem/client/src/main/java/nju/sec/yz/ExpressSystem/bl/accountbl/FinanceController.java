package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.tool.LogTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.LogVO;
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
		Finance finance=new Finance();
		BussinessVO vo=finance.checkBusinessCircumstance(begin, end);
		LogTool.setLog("制定经营情况表");
		return vo;
	}

	@Override
	public List<PaymentSheetVO> checkReceipt(String day, String positionId) {
		Collection in=new Collection();
		List<PaymentSheetVO> list=in.getByPosition(day, positionId);
		return list;
	}

	@Override
	/**
	 * 制定收款单
	 */
	public ResultMessage makeReceipt(PaymentSheetVO psv) {
		Collection collection=new Collection();
		ResultMessage message=collection.make(psv);
		LogTool.setLog("制定收款单");
		return message;
	}

	@Override
	public ResultMessage makePayment(OutVO pro) {
		Payment receipt=new Payment();
		ResultMessage messsge=receipt.make(pro);
		LogTool.setLog("制定付款单");
		return messsge;
	}

	@Override
	public ProfitVO makeCostReceipt() {
		Finance finance=new Finance();
		ProfitVO vo=finance.makeCostReceipt();
		return vo;
		
	}

	@Override
	public void exportCostToExcel(ProfitVO rv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportBussinessToExcel(BussinessVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	

}
