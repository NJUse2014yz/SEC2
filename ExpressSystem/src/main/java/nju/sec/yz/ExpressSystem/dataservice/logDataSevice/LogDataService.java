package nju.sec.yz.ExpressSystem.dataservice.logDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.LogPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

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
