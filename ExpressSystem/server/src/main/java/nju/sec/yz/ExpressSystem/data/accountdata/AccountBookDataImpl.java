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

import javax.swing.plaf.synth.SynthSpinnerUI;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;
import nju.sec.yz.ExpressSystem.po.CarPO;

public class AccountBookDataImpl extends UnicastRemoteObject implements AccountBookDataService{

	public AccountBookDataImpl() throws RemoteException {
		super();
	}

	@Override
	public ArrayList<AccountBookPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.ACCOUNT_BOOK_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<AccountBookPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

	@Override
	public AccountBookPO init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized ResultMessage update(AccountBookPO abp) throws RemoteException {
		System.out.println("updating a abp...");
		if(abp==null){
			System.out.println("更新的PO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=abp.getId();

		List<AccountBookPO> abps = findAll();
		for (int i = 0; i < abps.size(); i++) {
			AccountBookPO po = abps.get(i);
			String ID = po.getId();
			if (id.equals(ID)) {
				abps.remove(i);
				abps.add(abp);
				ResultMessage message=saveData(abps);
				return message;
			}

		}

		//未找到
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
	}

	@Override
	public synchronized ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a abp...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<AccountBookPO> abps = findAll();
		for (int i=0;i<abps.size();i++) {
			String ID = abps.get(i).getId();
			if (id.equals(ID)){
				abps.remove(i);
				ResultMessage message=saveData(abps);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public synchronized ResultMessage insert(AccountBookPO abp) throws RemoteException {
		System.out.println("inserting a AccountBookPO...");
		if(abp==null){
			System.out.println("插入了一个空的PO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<AccountBookPO> abps = findAll();
		
		
		abps.add(abp);

		ResultMessage message = saveData(abps);
		return message;
	}

	@Override
	public AccountBookPO find(String id) throws RemoteException {
		System.out.println("finding a abp...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<AccountBookPO> abps = findAll();
		for (AccountBookPO po : abps) {
			String ID = po.getId();
			if (id.equals(ID))
				return po;
		}
		
		return null;
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<AccountBookPO> POs){
		try {
			File file = SerializableFileHelper.getAccountBookFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(POs);
			}
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}

}
