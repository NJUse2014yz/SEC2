package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;

/**
 * 单据审批回复信息
 * @author 周聪
 *
 */
public class MessagePO implements Serializable{

	private String toPersonId;//收信息的人
	
	private String receiptId;//审批的单据的id
	
	private ReceiptType type;//审批的单据的类型
	
	private ReceiptOperation operation;//修改或通过
	
	private String makeTime;//提交时间
	
	private String other;//其他附加信息
	
	public MessagePO() {
		
	}
	
	
	//无附加信息
	public MessagePO(String toPersonId, String receiptId, ReceiptType type, ReceiptOperation operation,
			String makeTime) {
		super();
		this.toPersonId = toPersonId;
		this.receiptId = receiptId;
		this.type = type;
		this.operation = operation;
		this.makeTime = makeTime;
	}


	//有附加信息
	public MessagePO(String toPersonId, String receiptId, ReceiptType type, ReceiptOperation operation, String makeTime,
			String other) {
		super();
		this.toPersonId = toPersonId;
		this.receiptId = receiptId;
		this.type = type;
		this.operation = operation;
		this.makeTime = makeTime;
		this.other = other;
	}



	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public ReceiptType getType() {
		return type;
	}

	public void setType(ReceiptType type) {
		this.type = type;
	}

	public ReceiptOperation getOperation() {
		return operation;
	}

	public void setOperation(ReceiptOperation operation) {
		this.operation = operation;
	}

	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}

	public String getToPersonId() {
		return toPersonId;
	}

	public void setToPersonId(String toPersonId) {
		this.toPersonId = toPersonId;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	
}
