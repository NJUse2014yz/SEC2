package nju.sec.yz.ExpressSystem.data.deliverdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;

public class OrderDataImpl extends UnicastRemoteObject implements OrderDataService{

	public OrderDataImpl() throws RemoteException {
		super();
	}

	@Override
	public ResultMessage add(SendSheetPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SendSheetPO get(String barID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
