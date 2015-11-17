package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
/**
 * 
 * @author 周聪
 * 
 * 薪水策略信息
 *
 */
public class SalaryImformation implements Serializable{

	
	
	private Status power;
	private int salary;
	public SalaryImformation(Status power, int salary) {
		super();
		this.power = power;
		this.salary = salary;
	}
	public Status getPower() {
		return power;
	}
	public void setPower(Status power) {
		this.power = power;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	

	
	
	
	
}
