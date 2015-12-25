package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

/**
 * @author xiaosaisai
 * 薪水策略
 */
public interface SalaryBlService {
	
	/**
	 * 修改薪水策略
	 * @param sv 某个职务的薪水
	 * @return
	 */
	public ResultMessage modifySalary (SalaryVO sv);
	
	/**
	 * 查看所有职务的薪水
	 * @return
	 */
	public ArrayList<SalaryVO> observeSalary();
}
