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
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.CollectionRecordDataService;
import nju.sec.yz.ExpressSystem.po.CollectionRecordPO;
import nju.sec.yz.ExpressSystem.po.CollectionRecordPO;

public class CollectionRecordDataImpl extends UnicastRemoteObject implements CollectionRecordDataService{

	public CollectionRecordDataImpl() throws RemoteException {
		super();
		
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<CollectionRecordPO> CollectionRecordPOs){
		try {
			File file = SerializableFileHelper.getCollectionRecordFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(CollectionRecordPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	@Override
	public void addRecord(CollectionRecordPO po) throws RemoteException {
		System.out.println("inserting a CollectionRecordPO...");
		if(po==null){
			System.out.println("插入了一个空的CollectionRecordPO！！！");
			return;
		}
		
		List<CollectionRecordPO> CollectionRecordPOs = getRecords();
		for(CollectionRecordPO cpo:CollectionRecordPOs){
			if(po.getBarId().equals(cpo.getBarId())){
				System.out.println("重复");
				return;
			}
				
		}
		
		CollectionRecordPOs.add(po);

		saveData(CollectionRecordPOs);
		
		
	}

	@Override
	public void deleteRecord(String barId) throws RemoteException {
		System.out.println("deleting a CollectionRecordPO...");
		if(barId==null){
			System.out.println("id为null！！！");
			return;
		}
		List<CollectionRecordPO> CollectionRecordPOs = getRecords();
		for (int i=0;i<CollectionRecordPOs.size();i++) {
			String carID = CollectionRecordPOs.get(i).getBarId();
			if (barId.equals(carID)){
				CollectionRecordPOs.remove(i);
				saveData(CollectionRecordPOs);
				return ;
			}
				
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CollectionRecordDataImpl data=new CollectionRecordDataImpl();
			data.saveData(new ArrayList<CollectionRecordPO>());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private List<CollectionRecordPO> getRecords() {
		File file = new File(SerializableFileHelper.COLLECTION_RECORD_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<CollectionRecordPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

	@Override
	public List<CollectionRecordPO> getRecords(String positionId) throws RemoteException {
		List<CollectionRecordPO> pos=new ArrayList<>();
		List<CollectionRecordPO> allPO=this.getRecords();
		System.out.println(allPO.size());
		for(CollectionRecordPO po:allPO){
			if(po.getPositionId().equals(positionId))
				pos.add(po);
		}
		
		return pos;
	}

}
