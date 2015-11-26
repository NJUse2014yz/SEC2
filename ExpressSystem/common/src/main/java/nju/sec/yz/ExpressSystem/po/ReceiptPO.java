package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.ReceiptType;

/**
 * 
 * @author YU Fan
 *
 */
public class ReceiptPO implements Serializable {

	protected String id;
	/**
	 * 单据类型
	 */
	protected ReceiptType type;
	// 填单人和填单时间
	protected String makeTime;
	protected String makePerson;
	
	
	public ReceiptPO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReceiptType getType() {
		return type;
	}

	public void setType(ReceiptType type) {
		this.type = type;
	}

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
