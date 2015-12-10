package nju.sec.yz.ExpressSystem.data.receiptdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.MessagePO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;

public class MessageDataImpl extends UnicastRemoteObject implements MessageDataService{

	public MessageDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<MessagePO> MessagePOs){
		try {
			File file = SerializableFileHelper.getMessageFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(MessagePOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	
	
	private List<MessagePO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.MESSAGE_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<MessagePO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	@Override
	public synchronized void deleteMessage(String messageId) throws RemoteException {
		
		System.out.println("deleting a MessagePO...");
		if(messageId==null){
			System.out.println("id为null！！！");
			return;
		}
		List<MessagePO> messagePOs = findAll();
		for (int i=0;i<messagePOs.size();i++) {
			String ID = messagePOs.get(i).getMessageId();
			if (messageId.equals(ID)){
				messagePOs.remove(i);
				saveData(messagePOs);
				return ;
			}
				
		}
	}

	@Override
	public void addMessage(MessagePO po) throws RemoteException {
		System.out.println("inserting a MessagePO...");
		if(po==null){
			System.out.println("插入了一个空的orderPO！！！");
			return;
		}
		
		List<MessagePO> POs = findAll();
		POs.add(po);
		saveData(POs);
		
	}

	@Override
	public List<MessagePO> getMessages(String personId) throws RemoteException {
		List<MessagePO> messages=new ArrayList<>();
		List<MessagePO> pos = findAll();
		for(MessagePO po:pos){
			if(po.getToPersonId().equals(personId))
				messages.add(po);
		}
		
		return messages;
	}
	
	public static void main(String[] args) {
		try {
			MessageDataImpl message=new MessageDataImpl();
			List<MessagePO> pos=message.findAll();
			for(MessagePO po:pos){
				System.out.println(po.getToPersonId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
