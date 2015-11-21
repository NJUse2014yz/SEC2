package nju.sec.yz.ExpressSystem.data.accountdata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBookPO init() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(AccountBookPO abp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBookPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
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
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}

}
