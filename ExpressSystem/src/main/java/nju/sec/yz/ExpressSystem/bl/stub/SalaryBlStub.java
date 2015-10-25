package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class SalaryBlStub implements SalaryBlService {

	@Override
	public ResultMessage modifySalary(SalaryVO sv) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<SalaryVO> observeSalary() {
		// TODO 自动生成的方法存根
		return new ArrayList<SalaryVO>();
	}

}
