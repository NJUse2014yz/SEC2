package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.Content;
import nju.sec.yz.ExpressSystem.common.ReceiptOperation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;

public class MessageVO {
	
	public String messageId;//消息的id
	
	public String toPersonId;//收信息的人
	
	public String receiptId;//审批的单据的id
	
	public ReceiptType type;//审批的单据的类型
	
	public ReceiptOperation operation;//修改或通过
	
	public String makeTime;//单据提交时间
	
	public Content other;//其他附加信息
	
}
