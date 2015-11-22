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
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.TransitPO;


public class AgencyDataImpl extends UnicastRemoteObject implements AgencyDataService{

	public AgencyDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<TransitPO> TransitPOs){
		try {
			File file = SerializableFileHelper.getAgencyFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(TransitPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public ResultMessage insert(TransitPO agpo) throws RemoteException {
		System.out.println("inserting a TransitPO...");
		if(agpo==null){
			System.out.println("插入了一个空的TransitPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<TransitPO> TransitPOs = findAll();
		for(TransitPO po:TransitPOs){
			if(po.getId().equals(agpo.getId()))
				return new ResultMessage(Result.FAIL,"机构信息已存在");
		}
		
		TransitPOs.add(agpo);

		ResultMessage message = saveData(TransitPOs);
		return message;
	}

	@Override
	public TransitPO find(String id) throws RemoteException {
		System.out.println("finding a TransitPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<TransitPO> TransitPOs = findAll();
		for (TransitPO po : TransitPOs) {
			String carID = po.getId();
			if (id.equals(carID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a TransitPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<TransitPO> TransitPOs = findAll();
		for (int i=0;i<TransitPOs.size();i++) {
			String carID = TransitPOs.get(i).getId();
			if (id.equals(carID)){
				TransitPOs.remove(i);
				ResultMessage message=saveData(TransitPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public ResultMessage update(TransitPO agpo) throws RemoteException {
		System.out.println("updating a TransitPO...");
		if(agpo==null){
			System.out.println("更新的TransitPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=agpo.getId();

		List<TransitPO> TransitPOs = findAll();
		for (int i = 0; i < TransitPOs.size(); i++) {
			TransitPO po = TransitPOs.get(i);
			String carID = po.getId();
			if (id.equals(carID)) {
				TransitPOs.remove(i);
				TransitPOs.add(agpo);
				ResultMessage message=saveData(TransitPOs);
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
	public ArrayList<TransitPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.AGENCY_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<TransitPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
