package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;
/**
 * @author YU Fan
 * 注意初始化四个城市
 */
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

public interface ConstDataService {
	public ResultMessage update(CityPO cp) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public ArrayList<CityPO> findAllCity() throws RemoteException;
	public ResultMessage insert(CityPO cp) throws RemoteException;
	public ResultMessage delete(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage update(PricePO pp) throws RemoteException;
	public ArrayList<PricePO> findAllPrize() throws RemoteException;
}
