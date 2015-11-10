package nju.sec.yz.ExpressSystem.data.receiptdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

public class ReceiptDataImpl implements ReceiptDataService{

	@Override
	public ResultMessage insert(ReceiptPO rpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(ReceiptPO rpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReceiptPO> findByType(ReceiptType type) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
