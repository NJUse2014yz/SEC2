package nju.sec.yz.ExpressSystem.dataservice.accountDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface InDataService extends Remote{
	public ResultMessage insert(InPO inpo) throws RemoteException;
	public List<InPO> findByPosition(String date,String positionId) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(InPO inpo) throws RemoteException;
	public ResultMessage init() throws RemoteException;
	public List<InPO> findByTime(String begin,String end) throws RemoteException;
	public List<InPO> findAll() throws RemoteException;
}
