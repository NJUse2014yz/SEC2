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
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;

public class ReceiptDataImpl extends UnicastRemoteObject implements ReceiptDataService {

	public ReceiptDataImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage insert(ReceiptPO rpo) throws RemoteException {
		System.out.println("inserting a ReceiptPO...");
		if(rpo==null){
			System.out.println("插入了一个空的ReceiptPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<ReceiptPO> receiptPOs = findAll();
		receiptPOs.add(rpo);

		ResultMessage message = saveData(receiptPOs);
		return message;
	}

	@Override
	public ReceiptPO find(String id) throws RemoteException {
		System.out.println("finding a ReceiptPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return null;
		}
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
		System.out.println("updating a ReceiptPO...");
		if(rpo==null){
			System.out.println("更新的ReceiptPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
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
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
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
		System.out.println("deleting a ReceiptPO...");
		if(id==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		List<ReceiptPO> receiptPOs = findAll();
		for (int i=0;i<receiptPOs.size();i++) {
			String receiptID = receiptPOs.get(i).getId();
			if (id.equals(receiptID)){
				receiptPOs.remove(i);
				ResultMessage message=saveData(receiptPOs);
				return message;
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
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
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
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
	
	
	public static void main(String[] args) {
		try {
			ReceiptDataImpl receipt=new ReceiptDataImpl();
			List<ReceiptPO> list=receipt.findAll();
			for(ReceiptPO po:list){
				System.out.println(po.getType()+" "+po.getMakePerson()+" "+po.getId());
			}
			TransitLoadSheetPO po=(TransitLoadSheetPO)(list.get(4));
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
