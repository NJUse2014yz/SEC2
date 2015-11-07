package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;
import java.rmi.Remote;
/**
 * @author YU Fan
 * 注意初始化四个城市
 */
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

public interface ConstDataService extends Remote{
	public ResultMessage updateCity(CityPO cp) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public ArrayList<CityPO> findAllCity() throws RemoteException;
	public ResultMessage insert(CityPO cp) throws RemoteException;
	public ResultMessage delete(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage updatePrice(PricePO pp) throws RemoteException;
	public ArrayList<PricePO> findAllPrice() throws RemoteException;
}
