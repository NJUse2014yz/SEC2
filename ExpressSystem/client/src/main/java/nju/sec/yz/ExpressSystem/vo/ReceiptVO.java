package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.ReceiptType;

/**
 * 
 * @author YU Fan
 *单据
 */
public class ReceiptVO {
	protected String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 单据类型
	 */
	protected ReceiptType type;
	public ReceiptType getType() {
		return type;
	}
	public void setType(ReceiptType type) {
		this.type = type;
	}
	//填单人和填单时间
	protected String makeTime;
	protected String makePerson;
	public String getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
	public String getMakePerson() {
		return makePerson;
	}
	public void setMakePerson(String makePerson) {
		this.makePerson = makePerson;
	}
}
