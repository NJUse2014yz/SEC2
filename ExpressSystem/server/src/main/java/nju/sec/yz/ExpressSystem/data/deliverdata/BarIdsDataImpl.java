package nju.sec.yz.ExpressSystem.data.deliverdata;

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
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.BarIdsDataService;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;

public class BarIdsDataImpl extends UnicastRemoteObject implements BarIdsDataService{

	public BarIdsDataImpl() throws RemoteException {
		super();
		
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<BarIdsPO> barIdsPOs){
		try {
			File file = SerializableFileHelper.getBarIdsFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(barIdsPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	
	/**
	 * 获得所有条形码号列表
	 */
	private List<BarIdsPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.BAR_IDS_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<BarIdsPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

	@Override
	public void add(BarIdsPO po) throws RemoteException {
		System.out.println("inserting a BarIdsPO...");
		if(po==null){
			System.out.println("插入了一个空的BarIdsPO！！！");
			return;
		}
		
		List<BarIdsPO> BarIdsPOs = findAll();
		for(BarIdsPO cpo:BarIdsPOs){
			if(cpo.getReceiptId().equals(po.getReceiptId()))
				return;
		}
		
		BarIdsPOs.add(po);

		saveData(BarIdsPOs);
		
	}

	@Override
	public void delete(String transitId) throws RemoteException {
		System.out.println("deleting a BarIdsPO...");
		if(transitId==null){
			System.out.println("id为null！！！");
			return;
		}
		List<BarIdsPO> BarIdsPOs = findAll();
		for (int i=0;i<BarIdsPOs.size();i++) {
			String carID = BarIdsPOs.get(i).getReceiptId();
			if (transitId.equals(carID)){
				BarIdsPOs.remove(i);
				saveData(BarIdsPOs);
				return ;
			}
				
		}
		
	}

	@Override
	public BarIdsPO get(String transitId) throws RemoteException {
		System.out.println("finding a barID...");
		if(transitId==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<BarIdsPO> barIdsPOs = findAll();
		for (BarIdsPO po : barIdsPOs) {
			String carID = po.getReceiptId();
			if (transitId.equals(carID))
				return po;
		}
		
		
		return null;
	}
	@Override
	public void update(BarIdsPO po) throws RemoteException {
		List<BarIdsPO> list=this.findAll();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getReceiptId().equals(po.getReceiptId())){
				list.remove(i);
				list.add(po);
				return;
			}
		}
		
	}

	public static void main(String[] args) {
		try {
			BarIdsDataImpl data=new BarIdsDataImpl();
			List<BarIdsPO> list=data.findAll();
			for(BarIdsPO po:list){
				System.out.println(po.getReceiptId()+" "+ po.getDestinationId()+" "+po.getFromAgency());;
			}
			BarIdsPO po=list.get(2);
			po.setDestinationId("025001");
			data.saveData(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
