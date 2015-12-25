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
	 * 
	 */
	public ResultMessage modifySalary (SalaryVO sv);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<SalaryVO> observeSalary();
}
