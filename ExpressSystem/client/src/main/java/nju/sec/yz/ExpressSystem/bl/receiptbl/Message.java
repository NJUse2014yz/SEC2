package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.po.MessagePO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.MessageVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 处理消息的发送与保存
 * @author 周聪
 */
public class Message{

	private MessageDataService data;
	
	public Message() {
		try {
			data=DatafactoryProxy.getMessageDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存消息到数据层
	 */
	public void send(MessageVO vo) {
		System.out.println("sending a message");
		
		String messageId=this.createId(vo.toPersonId);
		
		MessagePO po=new MessagePO(messageId, vo.toPersonId, vo.message);

		try {
			data.addMessage(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 消息id规则：收件人id+"m"+五位数字
	 * @param personId
	 * @return
	 */
	private String createId(String personId){
		ReceiptID idMaker=new ReceiptID();
		String id=idMaker.getID(personId, IdType.MESSAGE);
		return id;
	}
	
	
	public List<MessageVO> getNewMessages() {
		UserInfo user=new User();
		String currentUser=user.getCurrentID();
		
		
		List<MessageVO> messages=new ArrayList<>();
		
		try {
			List<MessagePO> pos=data.getMessages(currentUser);
			System.out.println("get "+pos.size()+" messages");
			for(MessagePO po:pos){
				
				MessageVO message=new MessageVO(po.getMessageId(), po.getToPersonId(), po.getMessage());
				messages.add(message);
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return messages;
	}

	
	public void hasRead(String messageId) {
		try {
			data.deleteMessage(messageId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
	}

}
