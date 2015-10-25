package nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice;

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
public interface DeliverDataService {

	public ResultMessage insert(DeliverPO dpo) throws RemoteException;
	public ResultMessage update(DeliverPO dpo) throws RemoteException;
	public DeliverPO find() throws RemoteException;
	public ArrayList<DeliverPO> findAll() throws RemoteException;
}
