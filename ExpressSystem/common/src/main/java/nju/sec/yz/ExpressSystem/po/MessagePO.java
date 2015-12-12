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
	
	private String message;
	
	public MessagePO() {
		
	}

	public MessagePO(String messageId, String toPersonId, String message) {
		super();
		this.messageId = messageId;
		this.toPersonId = toPersonId;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
