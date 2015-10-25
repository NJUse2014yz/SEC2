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

	
	
	//总经理工资
	private int salaryOfManager;
	
	//高级财务人员工资
	private int salaryOfSeniorAccountancy;
	
	//初级财务人员工资
	private int salaryOfJuniorAccountancy;
	
	//中转中心业务员工资
	private int salaryOfTrancit;
	
	//营业厅业务员工资
	private int salaryOfPosition;
	
	//快递员工资
	private int salaryOfDeliver;
	
	//仓库管理人员工资
	private int salaryOfInventory;

	public SalaryImformation(int salaryOfManager, int salaryOfSeniorAccountancy, int salaryOfJuniorAccountancy,
			int salaryOfTrancit, int salaryOfPosition, int salaryOfDeliver, int salaryOfInventory) {
		super();
		this.salaryOfManager = salaryOfManager;
		this.salaryOfSeniorAccountancy = salaryOfSeniorAccountancy;
		this.salaryOfJuniorAccountancy = salaryOfJuniorAccountancy;
		this.salaryOfTrancit = salaryOfTrancit;
		this.salaryOfPosition = salaryOfPosition;
		this.salaryOfDeliver = salaryOfDeliver;
		this.salaryOfInventory = salaryOfInventory;
	}

	public int getSalaryOfManager() {
		return salaryOfManager;
	}

	public void setSalaryOfManager(int salaryOfManager) {
		this.salaryOfManager = salaryOfManager;
	}

	public int getSalaryOfSeniorAccountancy() {
		return salaryOfSeniorAccountancy;
	}

	public void setSalaryOfSeniorAccountancy(int salaryOfSeniorAccountancy) {
		this.salaryOfSeniorAccountancy = salaryOfSeniorAccountancy;
	}

	public int getSalaryOfJuniorAccountancy() {
		return salaryOfJuniorAccountancy;
	}

	public void setSalaryOfJuniorAccountancy(int salaryOfJuniorAccountancy) {
		this.salaryOfJuniorAccountancy = salaryOfJuniorAccountancy;
	}

	public int getSalaryOfTrancit() {
		return salaryOfTrancit;
	}

	public void setSalaryOfTrancit(int salaryOfTrancit) {
		this.salaryOfTrancit = salaryOfTrancit;
	}

	public int getSalaryOfPosition() {
		return salaryOfPosition;
	}

	public void setSalaryOfPosition(int salaryOfPosition) {
		this.salaryOfPosition = salaryOfPosition;
	}

	public int getSalaryOfDeliver() {
		return salaryOfDeliver;
	}

	public void setSalaryOfDeliver(int salaryOfDeliver) {
		this.salaryOfDeliver = salaryOfDeliver;
	}

	public int getSalaryOfInventory() {
		return salaryOfInventory;
	}

	public void setSalaryOfInventory(int salaryOfInventory) {
		this.salaryOfInventory = salaryOfInventory;
	}
	
	
	
	
}
