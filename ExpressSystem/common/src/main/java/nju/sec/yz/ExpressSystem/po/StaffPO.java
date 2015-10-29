package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.Power;

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
	private Power power;
	
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

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public AgencyPO getAgency() {
		return agency;
	}

	public void setAgency(AgencyPO agency) {
		this.agency = agency;
	}

	//所属机构
	private AgencyPO agency;

	public StaffPO(String name, String id, Power power, AgencyPO agency) {
		super();
		this.name = name;
		this.id = id;
		this.power = power;
		this.agency = agency;
	}
	
	
	
	
}
