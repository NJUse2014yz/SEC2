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
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryInDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;

public class InventoryInDataImpl extends UnicastRemoteObject implements InventoryInDataService{

	public InventoryInDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<InventoryInSheetPO> InventoryPOs){
		try {
			File file = SerializableFileHelper.getInventoryInFile();
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
	
	private List<InventoryInSheetPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.INVENTORY_IN_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<InventoryInSheetPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	@Override
	public ResultMessage insert(InventoryInSheetPO ipo) throws RemoteException {
		System.out.println("inserting a InventoryPO...");
		if(ipo==null){
			System.out.println("插入了一个空的InventoryPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<InventoryInSheetPO> InventoryPOs = findAll();
		for(InventoryInSheetPO po:InventoryPOs){
			//同一中转中心同一条形码号
			if(po.getBarId().equals(ipo.getBarId())&&po.getInventoryInInformation().getTransit().equals(ipo.getInventoryInInformation().getTransit()))
				return new ResultMessage(Result.FAIL,"库存信息已存在");
		}
		
		InventoryPOs.add(ipo);

		ResultMessage message = saveData(InventoryPOs);
		return message;
	}

	@Override
	public ResultMessage update(InventoryInSheetPO ipo) throws RemoteException {
		System.out.println("updating a InventoryPO...");
		if(ipo==null){
			System.out.println("更新的InventoryPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		String id=ipo.getBarId();

		List<InventoryInSheetPO> InventoryPOs = findAll();
		for (int i = 0; i < InventoryPOs.size(); i++) {
			InventoryInSheetPO po = InventoryPOs.get(i);
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
	public List<InventoryInSheetPO> findByTime(String transit, String timeIn, String timeOut) throws RemoteException {
		System.out.println("finding inventoryPO...");
		if(timeIn==null||timeOut==null){
			System.out.println("时间为null！！！");
			return null;
		}
		int begin=Integer.parseInt(timeIn);
		int end=Integer.parseInt(timeOut);
		List<InventoryInSheetPO> iPOs = findAll();
		ArrayList<InventoryInSheetPO> result=new ArrayList<>();
		for (InventoryInSheetPO po : iPOs) {
			if(!transit.equals(po.getInventoryInInformation().getTransit()))
				continue;
			int in=Integer.parseInt(po.getInventoryInInformation().getTime());
			if(in>=begin&&in<=end)
				result.add(po);
		}
		return result;
	}

}
