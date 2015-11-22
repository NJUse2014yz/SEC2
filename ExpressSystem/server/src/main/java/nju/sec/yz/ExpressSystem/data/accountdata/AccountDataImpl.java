package nju.sec.yz.ExpressSystem.data.accountdata;

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
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.po.CarPO;

public class AccountDataImpl extends UnicastRemoteObject implements AccountDataService{

	public AccountDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<AccountPO> accountPOs){
		try {
			File file = SerializableFileHelper.getAccountFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(accountPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public synchronized ResultMessage insert(AccountPO apo) throws RemoteException {
		System.out.println("inserting a AccountPO...");
		if(apo==null){
			System.out.println("插入了一个空的PO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<AccountPO> accountPOs = findAll();
		for(AccountPO po:accountPOs){
			if(po.getName().equals(apo.getName()))
				return new ResultMessage(Result.FAIL,"账户已存在");
		}
		
		accountPOs.add(apo);

		ResultMessage message = saveData(accountPOs);
		return message;
	}

	@Override
	public AccountPO find(String name) throws RemoteException {
		System.out.println("finding a carPO...");
		if(name==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<AccountPO> accountPOs = findAll();
		for (AccountPO po : accountPOs) {
			String account = po.getName();
			if (name.equals(account))
				return po;
		}
		
		
		return null;
	}

	@Override
	public synchronized ResultMessage delete(String name) throws RemoteException {
		System.out.println("deleting a accountPO...");
		if(name==null){
			System.out.println("name为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<AccountPO> accountPOs = findAll();
		for (int i=0;i<accountPOs.size();i++) {
			String carID = accountPOs.get(i).getName();
			if (name.equals(carID)){
				accountPOs.remove(i);
				ResultMessage message=saveData(accountPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public synchronized ResultMessage update(AccountPO apo) throws RemoteException {
		System.out.println("updating a CarPO...");
		if(apo==null){
			System.out.println("更新的AccountPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=apo.getName();

		List<AccountPO> accountPOs = findAll();
		for (int i = 0; i < accountPOs.size(); i++) {
			AccountPO po = accountPOs.get(i);
			String carID = po.getName();
			if (id.equals(carID)) {
				accountPOs.remove(i);
				accountPOs.add(apo);
				ResultMessage message=saveData(accountPOs);
				return message;
			}

		}

		//未找到
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.ACCOUNT_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<AccountPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
