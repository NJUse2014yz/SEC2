package nju.sec.yz.ExpressSystem.dataservice.logDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.LogPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface LogDataService extends Remote{
	public ResultMessage insert(LogPO lpo) throws RemoteException;
	/**
	 * 按日期查找
	 */
	public List<LogPO> find(String time) throws RemoteException;
	public ArrayList<LogPO> findAll( ) throws RemoteException;
}
