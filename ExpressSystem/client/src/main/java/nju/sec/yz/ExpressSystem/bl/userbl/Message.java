package nju.sec.yz.ExpressSystem.bl.userbl;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.po.MessagePO;
import nju.sec.yz.ExpressSystem.vo.MessageVO;

/**
 * 处理消息的发送与保存
 * @author 周聪
 */
public class Message implements MessageSendService{

	private MessageDataService data;
	
	public Message() {
		try {
			data=DatafactoryProxy.getMessageDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	@Override
	public void send(MessageVO vo) {
		String messageId=this.createId(vo.toPersonId);
		MessagePO po=new MessagePO(vo.toPersonId, vo.receiptId, vo.type, vo.operation, vo.makeTime, messageId);
	
		//TODO 附加信息
		
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
		
		
		
		return null;
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
