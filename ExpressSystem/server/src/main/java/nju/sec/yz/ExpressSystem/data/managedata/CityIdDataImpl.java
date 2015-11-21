package nju.sec.yz.ExpressSystem.data.managedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.po.CityIdPO;

public class CityIdDataImpl extends UnicastRemoteObject implements CityIdDataService{

	public CityIdDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCity(CityIdPO po) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CityIdPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
