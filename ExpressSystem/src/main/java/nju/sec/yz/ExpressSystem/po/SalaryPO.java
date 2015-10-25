package nju.sec.yz.ExpressSystem.po;
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import java.io.Serializable;
/**
 * 
 * @author 周聪
 *
 * 薪水策略
 */


public class SalaryPO implements Serializable{

	private SalaryImformation salaryImformation;

	public SalaryPO(SalaryImformation salaryImformation) {
		super();
		this.salaryImformation = salaryImformation;
	}

	public SalaryImformation getSalaryImformation() {
		return salaryImformation;
	}

	public void setSalaryImformation(SalaryImformation salaryImformation) {
		this.salaryImformation = salaryImformation;
	}
	
	
	
	
	
	
}
