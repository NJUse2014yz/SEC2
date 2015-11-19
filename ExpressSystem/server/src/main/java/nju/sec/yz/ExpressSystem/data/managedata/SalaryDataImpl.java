package nju.sec.yz.ExpressSystem.data.managedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.po.SalaryPO;

public class SalaryDataImpl extends UnicastRemoteObject implements SalaryDataService{

	public SalaryDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<SalaryPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(SalaryPO sp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
