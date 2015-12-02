package nju.sec.yz.ExpressSystem.vo;


import nju.sec.yz.ExpressSystem.common.ReceiptOperation;

import nju.sec.yz.ExpressSystem.po.ReceiptPO;

public class MessageVO {
	
	public String messageId;//消息的id
	
	public String toPersonId;//收信息的人
	
	public ReceiptOperation operation;//修改或通过
	
	public ReceiptVO receipt;//审批通过的单据

	public MessageVO(String toPersonId, ReceiptOperation operation, ReceiptVO receipt) {
		super();
		this.toPersonId = toPersonId;
		this.operation = operation;
		this.receipt = receipt;
	}
	
	public MessageVO(){
		
	}
	
	
}
