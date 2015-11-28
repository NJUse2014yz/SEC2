package nju.sec.yz.ExpressSystem.data.inventorydata;

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
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryPO;

public class InventoryDataImpl extends UnicastRemoteObject implements InventoryDataService{

	public InventoryDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<InventoryPO> InventoryPOs){
		try {
			File file = SerializableFileHelper.getInventoryFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(InventoryPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public synchronized ResultMessage insert(InventoryPO ipo) throws RemoteException {
		System.out.println("inserting a InventoryPO...");
		if(ipo==null){
			System.out.println("插入了一个空的InventoryPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<InventoryPO> InventoryPOs = findAll();
		for(InventoryPO po:InventoryPOs){
			if(po.getBarId().equals(ipo.getBarId()))
				return new ResultMessage(Result.FAIL,"库存信息已存在");
		}
		
		InventoryPOs.add(ipo);

		ResultMessage message = saveData(InventoryPOs);
		return message;
	}

	@Override
	public synchronized ResultMessage update(InventoryPO ipo) throws RemoteException {
		System.out.println("updating a InventoryPO...");
		if(ipo==null){
			System.out.println("更新的InventoryPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=ipo.getBarId();

		List<InventoryPO> InventoryPOs = findAll();
		for (int i = 0; i < InventoryPOs.size(); i++) {
			InventoryPO po = InventoryPOs.get(i);
			String carID = po.getBarId();
			if (id.equals(carID)) {
				InventoryPOs.remove(i);
				InventoryPOs.add(ipo);
				ResultMessage message=saveData(InventoryPOs);
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
	public ArrayList<InventoryPO> findByTime(String transit,String timeIn, String timeOut) throws RemoteException {
		System.out.println("finding inventoryPO...");
		if(timeIn==null||timeOut==null){
			System.out.println("时间为null！！！");
			return null;
		}
		int begin=Integer.parseInt(timeIn);
		int end=Integer.parseInt(timeOut);
		List<InventoryPO> iPOs = findAll();
		ArrayList<InventoryPO> result=new ArrayList<>();
		for (InventoryPO po : iPOs) {
			if(!transit.equals(po.getTransitId()))
				continue;
			int in=Integer.parseInt(po.getInventoryInformation().getTime());
			int out=Integer.parseInt(po.getInventoryOutInformation().getTime());
			if((in>=begin&&in<=end)||(out>=begin&&out<=end))
				result.add(po);
		}
		return result;
	}

	@Override
	public ArrayList<InventoryPO> findByTime(String transitId,String date) throws RemoteException {
		System.out.println("finding inventoryPO...");
		if(date==null){
			System.out.println("时间为null！！！");
			return null;
		}
		int today=Integer.parseInt(date);
		List<InventoryPO> iPOs = findAll();
		ArrayList<InventoryPO> result=new ArrayList<>();
		for (InventoryPO po : iPOs) {
			if(!transitId.equals(po.getTransitId()))
				continue;
			int in=Integer.parseInt(po.getInventoryInformation().getTime());
			int out=Integer.parseInt(po.getInventoryOutInformation().getTime());
			if((in==today)||(out==today))
				result.add(po);
		}
		return result;
	}

	@Override
	public ArrayList<InventoryPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.INVENTORY_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<InventoryPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}


}
