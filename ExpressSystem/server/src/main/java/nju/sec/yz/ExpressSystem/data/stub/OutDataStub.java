package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class OutDataStub implements  OutDataService{

	@Override
	public ResultMessage insert(OutPO outpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	
	public OutPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new OutPO("20151022",100000,"E001","A02","租金","南京中转中心2015年度");
	}

	
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(OutPO outpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<OutPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<OutPO> array=new ArrayList<OutPO>();
		array.add(new OutPO("20151022",100000,"E001","A02","租金","南京中转中心2015年度"));
		return array;
	}

	@Override
	public List<OutPO> findByTime(String begin, String end) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
