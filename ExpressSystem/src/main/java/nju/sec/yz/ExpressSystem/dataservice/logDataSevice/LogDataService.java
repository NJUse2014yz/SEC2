package nju.sec.yz.ExpressSystem.dataservice.logDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;

/**
 * 
 * @author zhangqi
 *
 */
public interface LogDataService {
	public ResultMessage insert(LogPO lpo) throws RemoteException;
	public LogPO find(String time) throws RemoteException;
	public ArrayList<UserPO> findAll( ) throws RemoteException;
}
