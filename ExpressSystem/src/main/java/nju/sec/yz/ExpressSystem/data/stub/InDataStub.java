package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.po.InPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class InDataStub implements InDataService{

	@Override
	public ResultMessage insert(InPO inpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public InPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new InPO("20151023",12,"025001D001");
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(InPO inpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<InPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<InPO> array=new ArrayList<InPO>();
		array.add(new InPO("20151023",12,"025001D001"));
		array.add(new InPO("20151024",30,"025002D004"));
		return array;
	}

}
