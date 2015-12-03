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
		
		// TODO
		financeBl_stub.checkReceipt(null, "9");

		// 导出excel
		financeBl_stub.exportCostToExcel(null);

		

		System.out.println("初期建账成功");
		// 成本收益表
		ProfitVO profitVO = financeBl_stub.makeCostReceipt();
		
		//
		ResultMessage paymentResult = financeBl_stub.makePayment(null);

		System.out.println("收款单创建成功");
		

	}

	// public static void main(String[] args) {
	// FinanceBlSevice financeBl_stub=new FinanceBlStub();
	// new FinanceBlDriver().drive(financeBl_stub);
	// }

}
