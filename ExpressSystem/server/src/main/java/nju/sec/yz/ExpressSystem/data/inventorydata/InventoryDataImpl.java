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

import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;

public class InventoryDataImpl extends UnicastRemoteObject implements InventoryDataService{

	public InventoryDataImpl() throws RemoteException {
		super();
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<InventoryInSheetPO> InventoryPOs){
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
	public synchronized ResultMessage insert(InventoryInSheetPO ipo) throws RemoteException {
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
	public ResultMessage init(List<InventoryInSheetPO> stocks) throws RemoteException {
		return this.saveData(stocks);
	}
	
	private List<InventoryInSheetPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.INVENTORY_FILE_NAME);
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
	public ResultMessage delete(String transit,String barId) throws RemoteException {
		System.out.println("deleting a carPO...");
		if(transit==null||barId==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<InventoryInSheetPO> inventoryInSheetPOs = findAll();
		for (int i=0;i<inventoryInSheetPOs.size();i++) {
			InventoryInInformation info = inventoryInSheetPOs.get(i).getInventoryInInformation();
			if (transit.equals(info.getTransit())&&barId.equals(inventoryInSheetPOs.get(i).getBarId())){
				inventoryInSheetPOs.remove(i);
				ResultMessage message=saveData(inventoryInSheetPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public List<InventoryInSheetPO> findAll(String transit) throws RemoteException {
		System.out.println("finding inventoryPO...");
		if(transit==null){
			System.out.println("id为null！！！");
			return null;
		}
		
		List<InventoryInSheetPO> iPOs = findAll();
		List<InventoryInSheetPO> result=new ArrayList<>();
		for (InventoryInSheetPO po : iPOs) {
			if(transit.equals(po.getInventoryInInformation().getTransit()))
				result.add(po);
		}
		return result;
	}


}
