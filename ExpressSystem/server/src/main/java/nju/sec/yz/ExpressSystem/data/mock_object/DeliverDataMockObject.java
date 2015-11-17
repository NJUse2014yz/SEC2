package nju.sec.yz.ExpressSystem.data.mock_object;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;

public class DeliverDataMockObject implements DeliverDataService{

	@Override
	public ResultMessage insert(DeliverPO dpo) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage update(DeliverPO dpo) throws RemoteException {
		return null;
	}

	

	@Override
	public ArrayList<DeliverPO> findAll() throws RemoteException {
		return new ArrayList<DeliverPO>();
	}

	@Override
	public DeliverPO find(String barID) throws RemoteException {
		return null;
	}
	
}
