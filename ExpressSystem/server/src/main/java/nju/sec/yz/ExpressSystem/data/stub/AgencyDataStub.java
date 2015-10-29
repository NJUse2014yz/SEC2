package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;
import nju.sec.yz.ExpressSystem.po.CarPO;

/**
 * 
 * @author zhangqi
 *
 */
public class AgencyDataStub implements AgencyDataService{

	@Override
	public ResultMessage insert(AgencyPO agpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public AgencyPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new AgencyPO("南京","001","南京中转中心");
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(AgencyPO agpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<AgencyPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<AgencyPO> array=new ArrayList<AgencyPO>();
		array.add(new AgencyPO("南京","001","南京中转中心"));
		return array;
	}

}
