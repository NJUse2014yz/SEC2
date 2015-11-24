package nju.sec.yz.ExpressSystem.data.logdata;

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
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.po.LogPO;
import nju.sec.yz.ExpressSystem.po.LogPO;

public class LogDataImpl extends UnicastRemoteObject implements LogDataService{

	public LogDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<LogPO> LogPOs){
		try {
			File file = SerializableFileHelper.getLogFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(LogPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public synchronized ResultMessage insert(LogPO lpo) throws RemoteException {
		System.out.println("inserting a LogPO...");
		if(lpo==null){
			System.out.println("插入了一个空的LogPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<LogPO> LogPOs = findAll();
		
		LogPOs.add(lpo);

		ResultMessage message = saveData(LogPOs);
		return message;
	}

	@Override
	/**
	 * 按日期查找 TODO
	 */
	public List<LogPO> find(String time) throws RemoteException {
		System.out.println("finding a LogPO...");
		if(time==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<LogPO> LogPOs = findAll();
		List<LogPO> result=new ArrayList<>();
		for (LogPO po : LogPOs) {
			String date = po.getTime();
			if (time.equals(date))
				result.add(po);
		}
		
		
		return result;
	}

	@Override
	public ArrayList<LogPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.LOG_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<LogPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
