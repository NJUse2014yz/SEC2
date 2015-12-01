package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.Content;
import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;

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
	
	
}
