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
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

public class CarDataImpl extends UnicastRemoteObject implements CarDataService{

	public CarDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage insert(CarPO cpo) throws RemoteException {
		System.out.println("inserting a CarPO...");
		if(cpo==null){
			System.out.println("插入了一个空的carPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<CarPO> carPOs = findAll();
		carPOs.add(cpo);

		ResultMessage message = saveData(carPOs);
		return message;
	}

	@Override
	public CarPO find(String id) throws RemoteException {
		System.out.println("finding a carPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<CarPO> carPOs = findAll();
		for (CarPO po : carPOs) {
			String carID = po.getId();
			if (id.equals(carID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		System.out.println("deleting a carPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<CarPO> carPOs = findAll();
		for (int i=0;i<carPOs.size();i++) {
			String carID = carPOs.get(i).getId();
			if (id.equals(carID)){
				carPOs.remove(i);
				ResultMessage message=saveData(carPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public ResultMessage update(CarPO cpo) throws RemoteException {
		System.out.println("updating a CarPO...");
		if(cpo==null){
			System.out.println("更新的CarPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=cpo.getId();

		List<CarPO> carPOs = findAll();
		for (int i = 0; i < carPOs.size(); i++) {
			CarPO po = carPOs.get(i);
			String carID = po.getId();
			if (id.equals(carID)) {
				carPOs.remove(i);
				carPOs.add(cpo);
				ResultMessage message=saveData(carPOs);
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
	public ArrayList<CarPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.CAR_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<CarPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<CarPO> carPOs){
		try {
			File file = SerializableFileHelper.getCarFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(carPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}

}
