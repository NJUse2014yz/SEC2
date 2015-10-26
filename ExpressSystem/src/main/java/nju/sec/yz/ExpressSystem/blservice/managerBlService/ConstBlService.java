package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;


/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	public ResultMessage modifyCity(CityVO cv) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public CityVO observeCity(String beginPlace,String endPlace) throws RemoteException;
	public ResultMessage addCity(CityVO cp) throws RemoteException;
	public ResultMessage deleteCity(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage modifyPrice(PriceVO pp) throws RemoteException;
	public PriceVO observePrize() throws RemoteException;
}
