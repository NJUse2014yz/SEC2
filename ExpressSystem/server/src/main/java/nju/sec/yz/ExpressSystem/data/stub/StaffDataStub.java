package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;
import nju.sec.yz.ExpressSystem.po.StaffPO;

/**
 * 
 * @author zhangqi
 *
 */
public class StaffDataStub implements StaffDataService{

	@Override
	public ResultMessage insert(StaffPO spo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public StaffPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new StaffPO("王明","E001",Status.JUNIOR_ACCOUNTANCY,new AgencyPO("000","000","总公司"));
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(StaffPO spo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<StaffPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
	ArrayList<StaffPO> array=new ArrayList<StaffPO>();
	array.add(new StaffPO("王明","E001",Status.JUNIOR_ACCOUNTANCY,new AgencyPO("000","000","总公司")));
	return array;
	}

}
