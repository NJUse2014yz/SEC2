package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.Power;


/**
 * 
 * @author 周聪
 *  人员信息
 */
public class StaffVO {

	// 姓名
	private String name;

	// 人员编号
	private String id;

	// 职位，权限
	private Power power;

	// 所属机构
	private String agency;
	
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

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}


}
