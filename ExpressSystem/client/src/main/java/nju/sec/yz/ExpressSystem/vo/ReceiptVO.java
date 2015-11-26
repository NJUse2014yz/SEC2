package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 
 * @author YU Fan 单据
 */
public class ReceiptVO {
	protected String id;
	/**
	 * 单据类型
	 */
	protected ReceiptType type;
	// 填单人和填单时间
	protected String makeTime;
	protected String makePerson;
	
	public ReceiptVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReceiptVO(ReceiptPO po){
		this.id=po.getId();
		this.makePerson=po.getMakePerson();
		this.makeTime=po.getMakeTime();
		this.type=po.getType();
	}
	
	public void copy(ReceiptPO po){
		this.id=po.getId();
		this.makePerson=po.getMakePerson();
		this.makeTime=po.getMakeTime();
		this.type=po.getType();
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
