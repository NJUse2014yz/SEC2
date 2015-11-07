package nju.sec.yz.ExpressSystem.data.deliverdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
/**
 * 
 * @author 周聪
 *
 */
public class DeliverData extends UnicastRemoteObject implements DeliverDataService{

	public DeliverData() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage insert(DeliverPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(DeliverPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeliverPO find() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DeliverPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
