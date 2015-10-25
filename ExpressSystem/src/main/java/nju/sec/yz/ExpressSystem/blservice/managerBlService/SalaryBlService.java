package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

/**
 * @author xiaosaisai
 * 薪水策略
 */
public interface SalaryBlService {
	public ResultMessage modifySalary (SalaryVO sv);
	public SalaryVO observeSalary();
}
