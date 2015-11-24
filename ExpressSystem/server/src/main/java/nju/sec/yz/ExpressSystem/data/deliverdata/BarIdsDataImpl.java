package nju.sec.yz.ExpressSystem.data.deliverdata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.BarIdsDataService;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.CarPO;

public class BarIdsDataImpl extends UnicastRemoteObject implements BarIdsDataService{

	public BarIdsDataImpl() throws RemoteException {
		super();
		
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<CarPO> carPOs){
		try {
			File file = SerializableFileHelper.getCarFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(carPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}

	@Override
	public void add(BarIdsPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String transitId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BarIdsPO get(String transitId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
