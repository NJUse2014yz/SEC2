package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class SalaryBlStub implements SalaryBlService {

	@Override
	public ResultMessage modifySalary(SalaryVO sv) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<SalaryVO> observeSalary() {
		// TODO 自动生成的方法存根
		ArrayList<SalaryVO> salaryStrategy=new ArrayList<>();
		SalaryVO salaryVO=new SalaryVO(new SalaryImformation(Status.MANAGER, 50));
		salaryStrategy.add(salaryVO);
		return salaryStrategy;
	}

}
