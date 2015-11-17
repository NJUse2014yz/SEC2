package nju.sec.yz.ExpressSystem.data.managedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;

public class ConstDataImpl implements ConstDataService{

	@Override
	public ResultMessage updateCity(CityPO cp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CityPO> findAllCity() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insert(CityPO cp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(String beginPlace, String endPlace) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updatePrice(PricePO pp) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PricePO> findAllPrice() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityPO find(String beginPlace, String endPlace) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
