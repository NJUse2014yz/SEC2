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
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
/**
 * 
 * @author 周聪
 *
 */
public class DeliverData extends UnicastRemoteObject implements DeliverDataService {
	
	public DeliverData() throws RemoteException {
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
	            ArrayList<DeliverPO> deliverPOs = findAll();
	            deliverPOs.add(dpo);

	            File file = SerializableFileHelper.getDeliverFile();
	            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
	                os.writeObject(deliverPOs);
	            }
	            System.out.println("success");
	            return ResultMessage.SUCCESS;
	        } catch (IOException e) {
	        	System.out.println("fail");
	            return ResultMessage.FAIL;
	        }
	}

	@Override
	public ResultMessage update(DeliverPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeliverPO find() throws RemoteException {
		// TODO Auto-generated method stub
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
            e.printStackTrace();
            return new ArrayList<>();
        }
	}

}
