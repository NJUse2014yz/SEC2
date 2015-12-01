package nju.sec.yz.ExpressSystem.dataservice.userDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.po.MessagePO;
/**
 * 保存消息
 * @author 周聪
 *
 */
public interface MessageDataService extends Remote{
	//TODO 查看历史消息
	
	/**
	 * 消息已读后删除
	 * @param messageId
	 * @throws RemoteException
	 */
	public void deleteMessage(String messageId) throws RemoteException;
	
	public void addMessage(MessagePO po) throws RemoteException;
	
	/**
	 * 通过当前用户id查找消息
	 * @param personId
	 * @return
	 * @throws RemoteException
	 */
	public List<MessagePO> getMessages(String personId) throws RemoteException;
	
	
	
}
