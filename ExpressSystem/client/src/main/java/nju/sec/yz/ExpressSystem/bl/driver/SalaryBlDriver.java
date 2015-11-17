package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.stub.SalaryBlStub;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

/**
 * 
 * @author 周聪
 *
 *         薪水
 *
 */
public class SalaryBlDriver {

	public void drive(SalaryBlService salaryBl_stub) {

		//
		ResultMessage resultMessage = salaryBl_stub.modifySalary(null);

		System.out.println("薪水策略修改成功");
		//
		List<SalaryVO> salaryStrategy = salaryBl_stub.observeSalary();
		SalaryVO manager = salaryStrategy.get(0);
		System.out.println("manager's salary is " + manager.getSalaryImformation().getSalary());

	}

	public static void main(String[] args) {
		SalaryBlService salaryBl_stub = new SalaryBlStub();
		new SalaryBlDriver().drive(salaryBl_stub);
	}

}
