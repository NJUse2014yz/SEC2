package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
<<<<<<< HEAD
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PrizeVO;
=======
import nju.sec.yz.ExpressSystem.po.CityPO;
>>>>>>> origin/master

/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	public ResultMessage update(CityVO cv) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public ArrayList<CityVO> findAllCity() throws RemoteException;
	public ResultMessage insert(CityVO cp) throws RemoteException;
	public ResultMessage delete(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage update(PrizeVO pp) throws RemoteException;
	public ArrayList<PrizeVO> findAllPrize() throws RemoteException;
}
