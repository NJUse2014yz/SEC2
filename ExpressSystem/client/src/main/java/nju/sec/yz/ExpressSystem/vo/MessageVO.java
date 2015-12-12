package nju.sec.yz.ExpressSystem.vo;


public class MessageVO {
	
	public String messageId;//消息的id
	
	public String toPersonId;//收信息的人
	
	public String message;//消息
	
	
	public MessageVO(String toPersonId, String message) {
		super();
		this.toPersonId = toPersonId;
		this.message = message;
	}

	public MessageVO(String messageId, String toPersonId, String message) {
		super();
		this.messageId = messageId;
		this.toPersonId = toPersonId;
		this.message = message;
	}


	public MessageVO(){
		
	}
	
	
}
