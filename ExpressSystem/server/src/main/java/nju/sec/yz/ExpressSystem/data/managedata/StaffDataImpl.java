package nju.sec.yz.ExpressSystem.data.managedata;

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
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.StaffPO;
import nju.sec.yz.ExpressSystem.po.StaffPO;

public class StaffDataImpl extends UnicastRemoteObject implements StaffDataService{

	public StaffDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<StaffPO> StaffPOs){
		try {
			File file = SerializableFileHelper.getStaffFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(StaffPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public ResultMessage insert(StaffPO spo) throws RemoteException {
		System.out.println("inserting a StaffPO...");
		if(spo==null){
			System.out.println("插入了一个空的StaffPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<StaffPO> StaffPOs = findAll();
		for(StaffPO po:StaffPOs){
			if(po.getId().equals(spo.getId()))
				return new ResultMessage(Result.FAIL,"人员信息已存在");
		}
		
		StaffPOs.add(spo);

		ResultMessage message = saveData(StaffPOs);
		return message;
	}

	@Override
	public StaffPO find(String id) throws RemoteException {
		System.out.println("finding a StaffPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<StaffPO> StaffPOs = findAll();
		for (StaffPO po : StaffPOs) {
			String carID = po.getId();
			if (id.equals(carID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a StaffPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<StaffPO> StaffPOs = findAll();
		for (int i=0;i<StaffPOs.size();i++) {
			String carID = StaffPOs.get(i).getId();
			if (id.equals(carID)){
				StaffPOs.remove(i);
				ResultMessage message=saveData(StaffPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public ResultMessage update(StaffPO spo) throws RemoteException {
		System.out.println("updating a StaffPO...");
		if(spo==null){
			System.out.println("更新的StaffPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=spo.getId();

		List<StaffPO> StaffPOs = findAll();
		for (int i = 0; i < StaffPOs.size(); i++) {
			StaffPO po = StaffPOs.get(i);
			String carID = po.getId();
			if (id.equals(carID)) {
				StaffPOs.remove(i);
				StaffPOs.add(spo);
				ResultMessage message=saveData(StaffPOs);
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
	public ArrayList<StaffPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.STAFF_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<StaffPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
