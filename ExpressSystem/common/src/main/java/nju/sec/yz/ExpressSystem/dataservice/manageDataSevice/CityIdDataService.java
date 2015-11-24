package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.po.CityIdPO;

/**
 * 保存所有城市名称和编号
 * @author 周聪
 *
 */
public interface CityIdDataService extends Remote{

	public void addCity(CityIdPO po) throws RemoteException;
	
	public List<CityIdPO> getAll() throws RemoteException;
	
}
