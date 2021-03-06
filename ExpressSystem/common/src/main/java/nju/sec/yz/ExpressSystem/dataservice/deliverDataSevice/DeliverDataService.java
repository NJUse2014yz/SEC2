package nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.po.UserPO;
/**
 * 
 * @author zhangqi
 *
 */
public interface DeliverDataService extends Remote{

	public ResultMessage insert(DeliverPO dpo) throws RemoteException;
	public ResultMessage update(DeliverPO dpo) throws RemoteException;
	public ArrayList<DeliverPO> findAll() throws RemoteException;
	DeliverPO find(String barID) throws RemoteException;
}
