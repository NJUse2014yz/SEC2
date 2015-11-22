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
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.po.OutPO;

public class OutDataImpl extends UnicastRemoteObject implements OutDataService{

	public OutDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<OutPO> outPOs){
		try {
			File file = SerializableFileHelper.getOutFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(outPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public ResultMessage insert(OutPO outpo) throws RemoteException {
		System.out.println("inserting a OutPO...");
		if(outpo==null){
			System.out.println("插入了一个空的OutPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<OutPO> outPOs = findAll();
		for(OutPO po:outPOs){
			if(po.getId().equals(outpo.getId()))
				return new ResultMessage(Result.FAIL,"车辆信息已存在");
		}
		
		outPOs.add(outpo);

		ResultMessage message = saveData(outPOs);
		return message;
	}

	@Override
	public OutPO find(String id) throws RemoteException {
		System.out.println("finding a OutPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<OutPO> OutPOs = findAll();
		for (OutPO po : OutPOs) {
			String carID = po.getId();
			if (id.equals(carID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a OutPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<OutPO> OutPOs = findAll();
		for (int i=0;i<OutPOs.size();i++) {
			String carID = OutPOs.get(i).getId();
			if (id.equals(carID)){
				OutPOs.remove(i);
				ResultMessage message=saveData(OutPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public ResultMessage update(OutPO outpo) throws RemoteException {
		System.out.println("updating a OutPO...");
		if(outpo==null){
			System.out.println("更新的OutPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=outpo.getId();

		List<OutPO> OutPOs = findAll();
		for (int i = 0; i < OutPOs.size(); i++) {
			OutPO po = OutPOs.get(i);
			String carID = po.getId();
			if (id.equals(carID)) {
				OutPOs.remove(i);
				OutPOs.add(outpo);
				ResultMessage message=saveData(OutPOs);
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
	public ArrayList<OutPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.OUT_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<OutPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
