package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.TransitPO;
import nju.sec.yz.ExpressSystem.po.CarPO;

/**
 * 
 * @author zhangqi
 *
 */
public class AgencyDataStub implements AgencyDataService{

	@Override
	public ResultMessage insert(TransitPO agpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public TransitPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new TransitPO("南京","001",null, "南京中转中心");
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(TransitPO agpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	

	@Override
	public ArrayList<TransitPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<TransitPO> array=new ArrayList<TransitPO>();
		array.add(new TransitPO("南京","001",null, "南京中转中心"));
		return array;
	}

	@Override
	public TransitPO findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage init(List<TransitPO> transits) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
