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
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InPO;

public class InDataImpl extends UnicastRemoteObject implements InDataService{

	public InDataImpl() throws RemoteException {
		super();
	}

	@Override
	public synchronized ResultMessage insert(InPO inpo) throws RemoteException {
		System.out.println("inserting a InPO...");
		if(inpo==null){
			System.out.println("插入了一个空的carPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<InPO> inPOs = findAll();
		
		
		inPOs.add(inpo);

		ResultMessage message = saveData(inPOs);
		return message;
	}

	@Override
	public List<InPO> findByPosition(String date,String positionId) throws RemoteException {
		System.out.println("finding a inPO...");
		if(positionId==null||date==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<InPO> inPOs = findAll();
		List<InPO> results=new ArrayList<>();
		for (InPO po : inPOs) {
			String position = po.getPosition();
			String inDate=po.getDate();
			if (positionId.equals(position)&&date.equals(inDate))
				results.add(po);
		}
		
		
		return results;
	}

	@Override
	public synchronized ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a inPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<InPO> inPOs = findAll();
		for (int i=0;i<inPOs.size();i++) {
			String inID = inPOs.get(i).getId();
			if (id.equals(inID)){
				inPOs.remove(i);
				ResultMessage message=saveData(inPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public synchronized ResultMessage update(InPO inpo) throws RemoteException {
		System.out.println("updating a InPO...");
		if(inpo==null){
			System.out.println("更新的InPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=inpo.getId();

		List<InPO> inPOs = findAll();
		for (int i = 0; i < inPOs.size(); i++) {
			InPO po = inPOs.get(i);
			String inID = po.getId();
			if (id.equals(inID)) {
				inPOs.remove(i);
				inPOs.add(inpo);
				ResultMessage message=saveData(inPOs);
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
	public ArrayList<InPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.IN_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<InPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<InPO> inPOs){
		try {
			File file = SerializableFileHelper.getInFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(inPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}

	@Override
	public List<InPO> findByTime(String begin, String end) throws RemoteException {
		System.out.println("finding a inPO...");
		if(begin==null||end==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<InPO> inPOs = findAll();
		List<InPO> results=new ArrayList<>();
		
		int min=Integer.parseInt(begin);
		int max=Integer.parseInt(end);
		for (InPO po : inPOs) {
			int date=Integer.parseInt(po.getDate());
			if (date>=min&&date<=max)
				results.add(po);
		}
		
		
		return results;
	}

}
