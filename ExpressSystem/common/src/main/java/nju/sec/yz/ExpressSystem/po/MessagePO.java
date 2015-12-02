package nju.sec.yz.ExpressSystem.po;
import java.io.Serializable;
import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
/**
 * 单据审批回复信息
 * @author 周聪
 *
 */
public class MessagePO implements Serializable{
	
	//TODO
	
	private String messageId;//标识消息的id

	private String toPersonId;//收信息的人
	
	private ReceiptOperation operation;//修改或通过
	
	private ReceiptPO receipt;//审批通过的单据
	
	public MessagePO() {
		
	}
	
	

	public MessagePO(String messageId, String toPersonId, ReceiptOperation operation, ReceiptPO receipt) {
		super();
		this.messageId = messageId;
		this.toPersonId = toPersonId;
		this.operation = operation;
		this.receipt = receipt;
	}







	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getToPersonId() {
		return toPersonId;
	}

	public void setToPersonId(String toPersonId) {
		this.toPersonId = toPersonId;
	}





	public ReceiptOperation getOperation() {
		return operation;
	}





	public void setOperation(ReceiptOperation operation) {
		this.operation = operation;
	}





	public ReceiptPO getReceipt() {
		return receipt;
	}





	public void setReceipt(ReceiptPO receipt) {
		this.receipt = receipt;
	}
	
	
}
