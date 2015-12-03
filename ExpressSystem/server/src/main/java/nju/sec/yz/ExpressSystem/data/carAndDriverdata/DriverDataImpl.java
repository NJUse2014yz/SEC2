package nju.sec.yz.ExpressSystem.data.carAndDriverdata;

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
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.DriverPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

public class DriverDataImpl extends UnicastRemoteObject implements DriverDataService{

	public DriverDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(DriverPO dpo) throws RemoteException {
		System.out.println("inserting a DriverPO...");
		if(dpo==null){
			System.out.println("插入了一个空的DriverPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<DriverPO> driverPOs = findAll();
		for(DriverPO po:driverPOs){
			if(po.getId().equals(dpo.getId()))
				return new ResultMessage(Result.FAIL,"司机信息已存在");
		}
		driverPOs.add(dpo);

		ResultMessage message = saveData(driverPOs);
		return message;
	}

	@Override
	public DriverPO find(String id) throws RemoteException {
		System.out.println("finding a driverPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<DriverPO> driverPOs = findAll();
		for (DriverPO po : driverPOs) {
			String driverID = po.getId();
			if (id.equals(driverID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a driverPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<DriverPO> driverPOs = findAll();
		for (int i=0;i<driverPOs.size();i++) {
			String carID = driverPOs.get(i).getId();
			if (id.equals(carID)){
				driverPOs.remove(i);
				ResultMessage message=saveData(driverPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public ResultMessage update(DriverPO dpo) throws RemoteException {
		System.out.println("updating a DriverPO...");
		if(dpo==null){
			System.out.println("更新的DriverPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=dpo.getId();

		List<DriverPO> driverPOs = findAll();
		for (int i = 0; i < driverPOs.size(); i++) {
			DriverPO po = driverPOs.get(i);
			String driverID = po.getId();
			if (id.equals(driverID)) {
				driverPOs.remove(i);
				driverPOs.add(dpo);
				ResultMessage message=saveData(driverPOs);
				return message;
			}

		}

		//未找到
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
	}

	@Override
	public ResultMessage init(List<DriverPO> init) throws RemoteException {
		
		ResultMessage message=this.saveData(init);
		return message;
	}

	@Override
	public ArrayList<DriverPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.DRIVER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<DriverPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<DriverPO> driverPOs){
		try {
			File file = SerializableFileHelper.getDriverFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(driverPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}


}
