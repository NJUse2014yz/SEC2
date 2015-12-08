package nju.sec.yz.ExpressSystem.data.managedata;

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
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.po.SalaryPO;
import nju.sec.yz.ExpressSystem.po.SalaryPO;

public class SalaryDataImpl extends UnicastRemoteObject implements SalaryDataService{

	public SalaryDataImpl() throws RemoteException {
		super();
	}
	
	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(List<SalaryPO> SalaryPOs){
		try {
			File file = SerializableFileHelper.getSalaryFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(SalaryPOs);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	private ArrayList<SalaryPO> init(){
		ArrayList<SalaryPO> pos=new ArrayList<>();
		pos.add(new SalaryPO(new SalaryImformation(Status.ADMINISTRATOR, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.DELIVER, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.INVENTORY, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.JUNIOR_ACCOUNTANCY, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.MANAGER, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.POSITION, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.SENIOR_ACCOUNTANCY, 0)));
		pos.add(new SalaryPO(new SalaryImformation(Status.TRANSIT, 0)));
		return pos;
	}

	@Override
	public ArrayList<SalaryPO> findAll() throws RemoteException {
		File file = new File(SerializableFileHelper.SALARY_FILE_NAME);
        if (!file.exists()) {
            return this.init();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<SalaryPO>) is.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
	}

	@Override
	public ResultMessage update(SalaryPO sp) throws RemoteException {
		System.out.println("updating a SalaryPO...");
		if(sp==null){
			System.out.println("更新的SalaryPO是空的！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
			
		Status status=sp.getSalaryImformation().getPower();

		List<SalaryPO> SalaryPOs = findAll();
		for (int i = 0; i < SalaryPOs.size(); i++) {
			SalaryPO po = SalaryPOs.get(i);
			Status status2=po.getSalaryImformation().getPower();
			if (status.equals(status2)) {
				SalaryPOs.remove(i);
				SalaryPOs.add(sp);
				ResultMessage message=saveData(SalaryPOs);
				return message;
			}

		}

		//未找到
		return new ResultMessage(Result.FAIL, "找不到要更新的内容");
	}

}
