package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;

/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	public ResultMessage update(CityPO cp) throws RemoteException;
	//public CityPO find(String beginPlace,String endPlace) throws RemoteException;
	public ArrayList<CityPO> findAll() throws RemoteException;
	public ResultMessage insert(CityPO cp) throws RemoteException;
	public ResultMessage delete(String beginPlace,String endPlace) throws RemoteException;
	
	public ResultMessage update(PrizePO pp) throws RemoteException;
	public ArrayList<PrizePO pp> findAll() throws RemoteException;
}
