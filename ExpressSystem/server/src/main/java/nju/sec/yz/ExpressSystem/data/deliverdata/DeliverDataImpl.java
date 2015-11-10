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

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
/**
 * 
 * @author 周聪
 *
 */
public class DeliverDataImpl extends UnicastRemoteObject implements DeliverDataService {
	
	public DeliverDataImpl() throws RemoteException {
		super();
	}

	@Override
	public synchronized ResultMessage insert(DeliverPO dpo) throws RemoteException {
		System.out.println("inserting a DeliverPO...");
		if(dpo==null){
			System.out.println("fail");
			return ResultMessage.FAIL;
		}
		try {
			List<DeliverPO> deliverPOs = findAll();
			deliverPOs.add(dpo);

			File file = SerializableFileHelper.getDeliverFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(deliverPOs);
			}
			System.out.println("success");
			return ResultMessage.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}

	@Override
	public ResultMessage update(DeliverPO dpo) throws RemoteException {
		if(dpo==null)
			return ResultMessage.FAIL;
		String barID=dpo.getOrdermation().getSendInformation().getBarId();
		try{
			List<DeliverPO> deliverPOs=findAll();
			for(int i=0;i<deliverPOs.size();i++){
				DeliverPO po=deliverPOs.get(i);
				String id=po.getOrdermation().getSendInformation().getBarId();
				if(id.equals(barID)){
					deliverPOs.remove(i);
					deliverPOs.add(dpo);
					return ResultMessage.SUCCESS;
				}
					
			}
		}catch(RemoteException e){
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		//未找到
		return ResultMessage.FAIL;
	}

	@Override
	public DeliverPO find(String barID) throws RemoteException {
		try{
			List<DeliverPO> deliverPOs=findAll();
			for(DeliverPO po:deliverPOs){
				String id=po.getOrdermation().getSendInformation().getBarId();
				if(id.equals(barID))
					return po;
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DeliverPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.DELIVER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<DeliverPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

}
