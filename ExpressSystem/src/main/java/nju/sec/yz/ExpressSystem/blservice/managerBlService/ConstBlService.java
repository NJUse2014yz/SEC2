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
	public ResultMessage updateCity(CityVO cv) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public ArrayList<CityVO> findAllCity() throws RemoteException;
	public ResultMessage insert(CityVO cp) throws RemoteException;
	public ResultMessage delete(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage updatePrice(PriceVO pp) throws RemoteException;
	public ArrayList<PriceVO> findAllPrize() throws RemoteException;
}
