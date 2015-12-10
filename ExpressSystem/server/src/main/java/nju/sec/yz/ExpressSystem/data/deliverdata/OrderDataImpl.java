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
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;

public class OrderDataImpl extends UnicastRemoteObject implements OrderDataService{

	public OrderDataImpl() throws RemoteException {
		super();
	}

	@Override
	public synchronized ResultMessage add(SendSheetPO po) throws RemoteException {
		System.out.println("inserting a OrderPO...");
		if(po==null){
			System.out.println("插入了一个空的orderPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		List<SendSheetPO> POs = findAll();
		POs.add(po);

		ResultMessage message = saveData(POs);
		return message;
	}

	@Override
	public SendSheetPO get(String barID) throws RemoteException {
		System.out.println("finding a OrderPO...");
		if(barID==null){
			System.out.println("id为null！！！");
			return null;
		}
		List<SendSheetPO> POs = findAll();
		for (SendSheetPO po : POs) {
			String carID = po.getSendInformation().getBarId();
			if (barID.equals(carID))
				return po;
		}
		
		
		return null;
	}

	
	private List<SendSheetPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.ORDER_FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (List<SendSheetPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}
	
	public static void main(String[] args) {
		try {
			OrderDataImpl data=new OrderDataImpl();
			List<SendSheetPO> pos=data.findAll();
			for(SendSheetPO po:pos){
				System.out.println(po.getSendInformation().getBarId());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<SendSheetPO> orderPOs){
		try {
			File file = SerializableFileHelper.getOrderFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(orderPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
}
