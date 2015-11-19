package nju.sec.yz.ExpressSystem.data.userdata;

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
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

public class UserDataImpl extends UnicastRemoteObject implements UserDataService{

	public UserDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized ResultMessage insert(UserPO upo) throws RemoteException {
		System.out.println("inserting a UserPO...");
		if(upo==null){
			System.out.println("插入了空UserPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<UserPO> userPOs = findAll();
		userPOs.add(upo);
		ResultMessage message=saveData(userPOs);
		return message;
	}

	@Override
	public UserPO find(String id) throws RemoteException {
		System.out.println("finding a UserPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<UserPO> userPOs = findAll();
		
		for (UserPO po : userPOs) {
			String userID = po.getId();	
			if (id.equals(userID)){
				return po;
			}
				
		}
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a UserPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<UserPO> userPOs = findAll();
		for (int i=0;i<userPOs.size();i++) {
			String userID = userPOs.get(i).getId();
			if (id.equals(userID)){
				userPOs.remove(i);
				ResultMessage message=saveData(userPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public synchronized ResultMessage update(UserPO upo) throws RemoteException {
		System.out.println("updating a UserPO...");
		if(upo==null){
			System.out.println("更新的UserPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=upo.getId();

		List<UserPO> userPOs = findAll();
		for (int i = 0; i < userPOs.size(); i++) {
			UserPO po = userPOs.get(i);
			String receiptID = po.getId();
			if (id.equals(receiptID)) {
				userPOs.remove(i);
				userPOs.add(upo);
				ResultMessage message=saveData(userPOs);
				return message;
			}

		}

		//未找到
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
	}

	@Override
	public synchronized ResultMessage init() throws RemoteException {
		// TODO init
		return null;
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<UserPO> userPOs){
		try {
			File file = SerializableFileHelper.getUserFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(userPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写出错");
		}
	}
	
	@Override
	public ArrayList<UserPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.USER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<UserPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
