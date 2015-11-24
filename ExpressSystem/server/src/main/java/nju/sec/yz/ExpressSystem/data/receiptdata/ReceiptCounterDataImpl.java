package nju.sec.yz.ExpressSystem.data.receiptdata;

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

import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptCountPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

public class ReceiptCounterDataImpl extends UnicastRemoteObject implements ReceiptCounterDataService{

	public ReceiptCounterDataImpl() throws RemoteException {
		super();
	}

	@Override
	public synchronized void add(ReceiptCountPO po) throws RemoteException {
		System.out.println("inserting a CounterPO...");
		if(po==null){
			System.out.println("插入了空PO！！！");
			return;
		}
		List<ReceiptCountPO> POs = findAll();
		POs.add(po);
		saveData(POs);
	}

	@Override
	public synchronized void update(ReceiptCountPO po) throws RemoteException {
		System.out.println("updating a CounterPO...");
		if(po==null){
			System.out.println("更新的UserPO是空的！！！");
			return;
		}
			
		String id=po.getId();
		IdType type=po.getReceiptType();

		List<ReceiptCountPO> POs = findAll();
		for (int i = 0; i < POs.size(); i++) {
			ReceiptCountPO updatePO = POs.get(i);
			if (id.equals(updatePO.getId())&&type.equals(updatePO.getReceiptType())) {
				updatePO.setCount(po.getCount());
				updatePO.setDate(po.getDate());
				saveData(POs);
				return;
			}

		}
		
	}

	@Override
	public ReceiptCountPO get(String id, IdType type) throws RemoteException {
		System.out.println("finding a CounterPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<ReceiptCountPO> POs = findAll();
		for (ReceiptCountPO po : POs) {
			if (id.equals(po.getId())&&type.equals(po.getReceiptType()))
				return po;
		}
		
		return null;
		
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized void saveData(List<ReceiptCountPO> POs){
		try {
			File file = SerializableFileHelper.getReceiptCounterFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(POs);
			}
			System.out.println("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<ReceiptCountPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.RECEIPT_COUNTER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<ReceiptCountPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
