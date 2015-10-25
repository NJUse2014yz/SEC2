package nju.sec.yz.ExpressSystem.blservice.receiptBlService;

import nju.sec.yz.ExpressSystem.common.ResultMessage;

/**
 * @author xiaosaisai
 * 薪水策略管理--改和查
 */
public interface SalaryBlService {
	public ResultMessage modifySalary (SalaryVO sv);
	public SalaryVO observeSalary();
}
