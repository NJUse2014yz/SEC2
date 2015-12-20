package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.Status;

/**
 * 
 * @author 周聪
 * 姓名、人员编号、职位、所属机构、权限
 */
public class StaffPO implements Serializable{

	//姓名
	private String name;
	
	//人员编号
	private String id;
	
	//职位，权限
	private Status power;
	
	//所属机构
	private String agency;
	
	private String loginId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getPower() {
		return power;
	}

	public void setPower(Status power) {
		this.power = power;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	

	public StaffPO(String name, String id, Status power, String agency) {
		super();
		this.name = name;
		this.id = id;
		this.power = power;
		this.agency = agency;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
