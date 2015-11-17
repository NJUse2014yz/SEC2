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

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

public class ReceiptDataImpl extends UnicastRemoteObject implements ReceiptDataService {

	public ReceiptDataImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage insert(ReceiptPO rpo) throws RemoteException {
		System.out.println("inserting a ReceiptPO...");
		if(rpo==null){
			System.out.println("fail");
			return ResultMessage.FAIL;
		}
		
		List<ReceiptPO> receiptPOs = findAll();
		receiptPOs.add(rpo);

		ResultMessage message = saveData(receiptPOs);
		return message;
	}

	@Override
	public ReceiptPO find(String id) throws RemoteException {
		
		List<ReceiptPO> receiptPOs = findAll();
		for (ReceiptPO po : receiptPOs) {
			String receiptID = po.getId();
			if (id.equals(receiptID))
				return po;
		}
		
		
		return null;
	}

	@Override
	public ResultMessage update(ReceiptPO rpo) throws RemoteException {
		if(rpo==null)
			return ResultMessage.FAIL;
		String id=rpo.getId();

		List<ReceiptPO> receiptPOs = findAll();
		for (int i = 0; i < receiptPOs.size(); i++) {
			ReceiptPO po = receiptPOs.get(i);
			String receiptID = po.getId();
			if (id.equals(receiptID)) {
				receiptPOs.remove(i);
				receiptPOs.add(rpo);
				ResultMessage message=saveData(receiptPOs);
				return message;
			}

		}

		//未找到
		return ResultMessage.FAIL;
	}

	@Override
	public ArrayList<ReceiptPO> findByType(ReceiptType type) throws RemoteException {
		if(type==null)
			return null;
		ArrayList<ReceiptPO> receiptPOs=new ArrayList<>();
		List<ReceiptPO> allPOs=findAll();
		for(ReceiptPO po:allPOs){
			if(po.getType()==type)
				receiptPOs.add(po);
		}
		return receiptPOs;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		List<ReceiptPO> receiptPOs = findAll();
		for (int i=0;i<receiptPOs.size();i++) {
			String receiptID = receiptPOs.get(i).getId();
			if (id.equals(receiptID)){
				receiptPOs.remove(i);
				ResultMessage message=saveData(receiptPOs);
				return message;
			}
				
		}
		return ResultMessage.FAIL;
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<ReceiptPO> receiptPOs){
		try {
			File file = SerializableFileHelper.getReceiptFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(receiptPOs);
			}
			System.out.println("success");
			return ResultMessage.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}
	
	@Override
	public List<ReceiptPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.RECEIPT_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<ReceiptPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	

}
