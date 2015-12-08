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

import javax.tools.ToolProvider;

import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.SerializableFileHelper;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

public class ConstDataImpl extends UnicastRemoteObject implements ConstDataService{

	
	public ConstDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 保存数据到文件
	 */
	private synchronized ResultMessage saveData(ConstDao dao){
		try {
			File file = SerializableFileHelper.getConstFile();
			try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
				os.writeObject(dao);
			}
			System.out.println("success");
			return new ResultMessage(Result.SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "文件读写错误");
		}
	}
	
	
	private ConstDao getDao() throws RemoteException {
		File file = new File(SerializableFileHelper.CONST_FILE_NAME);
        if (!file.exists()) {
            return new ConstDao();
        }
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            //noinspection unchecked
            return (ConstDao) is.readObject();
        } catch (Exception e) {
            return new ConstDao();
        }
	}
	
	
	@Override
	public synchronized ResultMessage updateCity(CityPO cp) throws RemoteException {

		System.out.println("deleting a cityPO...");
		if(cp==null){
			System.out.println("po为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		ConstDao dao=this.getDao();
		List<CityPO> cityPOs=dao.getDistances();
		
		String beginPlace=cp.getCityInformation().getFromCity();
		String endPlace=cp.getCityInformation().getToCity();
		
		for (int i=0;i<cityPOs.size();i++) {
			CityPO po=cityPOs.get(i);
			CityInformation info = po.getCityInformation();
			if (beginPlace.equals(info.getFromCity()) && endPlace.equals(info.getToCity())){
				cityPOs.remove(i);
				cityPOs.add(cp);
				dao.setDistances(cityPOs);
				return this.saveData(dao);
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
		
	}

	@Override
	public List<CityPO> findAllCity() throws RemoteException {
		System.out.println("finding a CityPO...");
		ConstDao dao=this.getDao();
		List<CityPO> pos=dao.getDistances();
		return pos;
	}

	@Override
	public synchronized ResultMessage insert(CityPO cpo) throws RemoteException {
		System.out.println("inserting a CityPO...");
		if(cpo==null){
			System.out.println("插入了一个空的cityPO！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		
		ConstDao dao=this.getDao();
		List<CityPO> cityPOs=dao.getDistances();
		
		CityInformation info=cpo.getCityInformation();
		for (CityPO po : cityPOs) {
			CityInformation info2 = po.getCityInformation();
			if (info2.getFromCity().equals(info.getFromCity()) && info2.getToCity().equals(info.getToCity()))
				return new ResultMessage(Result.FAIL, "常量已存在");
		}
		
		cityPOs.add(cpo);
		dao.setDistances(cityPOs);
		ResultMessage message = saveData(dao);
		return message;
	}

	@Override
	public synchronized ResultMessage delete(String beginPlace, String endPlace) throws RemoteException {
		
		
		System.out.println("deleting a cityPO...");
		if(beginPlace==null||endPlace==null){
			System.out.println("id为null！！！");
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		ConstDao dao=this.getDao();
		List<CityPO> cityPOs=dao.getDistances();
		
		for (int i=0;i<cityPOs.size();i++) {
			CityPO po=cityPOs.get(i);
			CityInformation info = po.getCityInformation();
			if (beginPlace.equals(info.getFromCity()) && endPlace.equals(info.getToCity())){
				cityPOs.remove(i);
				dao.setDistances(cityPOs);
				return this.saveData(dao);
			}
				
		}
		return new ResultMessage(Result.FAIL, "找不到要删除的内容");
	}

	@Override
	public synchronized ResultMessage updatePrice(PricePO pp) throws RemoteException {
		ConstDao dao=this.getDao();
		dao.setPrice(pp);
		ResultMessage message=saveData(dao);
		return message;
	}

	@Override
	public PricePO findAllPrice() throws RemoteException {
		ConstDao dao=this.getDao();
		return dao.getPrice();
	}

	@Override
	public synchronized CityPO find(String beginPlace, String endPlace) throws RemoteException {
		System.out.println("finding a cityPO...");
		if(beginPlace==null||endPlace==null){
			System.out.println("null！！！");
			return null;
		}
		List<CityPO> cityPOs = findAllCity();
		for (CityPO po : cityPOs) {
			String from=po.getCityInformation().getFromCity();
			String to=po.getCityInformation().getToCity();
			if(from.equals(beginPlace)&&to.equals(endPlace))
				return po;
		}
		
		
		return null;
	}

}
