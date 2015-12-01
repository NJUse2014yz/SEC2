package nju.sec.yz.ExpressSystem.data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.po.MessagePO;

public class MessageDataImpl extends UnicastRemoteObject implements MessageDataService{

	public MessageDataImpl() throws RemoteException {
		super();
	}

	@Override
	public void deleteMessage(String messageId) throws RemoteException {
		
		
	}

	@Override
	public void addMessage(MessagePO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MessagePO> getMessages(String personId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
