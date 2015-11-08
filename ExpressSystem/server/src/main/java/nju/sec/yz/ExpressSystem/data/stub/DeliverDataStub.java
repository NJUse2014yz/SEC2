package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;

public class DeliverDataStub implements DeliverDataService{

	@Override
	public ResultMessage insert(DeliverPO dpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(DeliverPO dpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	

	@Override
	public ArrayList<DeliverPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		return new ArrayList<DeliverPO>();
	}

	@Override
	public DeliverPO find(String barID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
