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
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.po.CityIdPO;
import nju.sec.yz.ExpressSystem.po.CityIdPO;

public class CityIdDataImpl extends UnicastRemoteObject implements CityIdDataService{

	public CityIdDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存数据到文件
	 */
	private synchronized void saveData(List<CityIdPO> CityIdPOs){
		try {
			File file = SerializableFileHelper.getCityIdFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(CityIdPOs);
			}
			System.out.println("success");
			return ;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public synchronized void addCity(CityIdPO cipo) throws RemoteException {
		System.out.println("inserting a CityIdPO...");
		if(cipo==null){
			System.out.println("插入了一个空的CityIdPO！！！");
			
		}
		
		List<CityIdPO> CityIdPOs = getAll();
		for(CityIdPO po:CityIdPOs){
			if(po.getId().equals(cipo.getId()))
				return ;
		}
		
		CityIdPOs.add(cipo);

		saveData(CityIdPOs);
		
		
	}
	
	
	/**
	 * 初始化
	 * 添加现有的城市
	 */
	private List<CityIdPO> init(){
		List<CityIdPO> pos=new ArrayList<>();
		pos.add(new CityIdPO("南京", "025"));
		pos.add(new CityIdPO("北京", "010"));
		pos.add(new CityIdPO("广州", "020"));
		pos.add(new CityIdPO("上海", "021"));
		
		return pos;
	}

	@Override
	public List<CityIdPO> getAll() throws RemoteException {
		File file = new File(SerializableFileHelper.CITYID_FILE_NAME);
        if (!file.exists()) {
            return this.init();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ArrayList<CityIdPO>) is.readObject();
        } catch (Exception e) {
            return this.init();
        }
	}

}
