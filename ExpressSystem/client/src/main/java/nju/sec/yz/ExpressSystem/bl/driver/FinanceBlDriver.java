package nju.sec.yz.ExpressSystem.bl.driver;

import nju.sec.yz.ExpressSystem.bl.stub.FinanceBlStub;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;

/**
 * 
 * @author 周聪
 *
 *         财务逻辑测试驱动
 */
public class FinanceBlDriver {

	public void drive(FinanceBlSevice financeBl_stub) {
		// 查看经营情况表
		BussinessVO bussinessVO = financeBl_stub.checkBusinessCircumstance(null, null);
		System.out.println("收款单：" + bussinessVO.getIn().get(0));
		// TODO
		financeBl_stub.checkReceipt(null, "9");

		// 导出excel
		financeBl_stub.exportCostToExcel(null);

		//
		ResultMessage initialResult = financeBl_stub.initial(null);

		System.out.println("初期建账成功");
		// 成本收益表
		ProfitVO profitVO = financeBl_stub.makeCostReceipt();
		System.out.println("总收入:" + profitVO.getIn());
		//
		ResultMessage paymentResult = financeBl_stub.makePayment(null);

		System.out.println("收款单创建成功");
		// 查看初期建账
		InitialVO initialVO = financeBl_stub.observeIni();
		System.out.println("机构：" + initialVO.getAgency());

	}

	// public static void main(String[] args) {
	// FinanceBlSevice financeBl_stub=new FinanceBlStub();
	// new FinanceBlDriver().drive(financeBl_stub);
	// }

}
