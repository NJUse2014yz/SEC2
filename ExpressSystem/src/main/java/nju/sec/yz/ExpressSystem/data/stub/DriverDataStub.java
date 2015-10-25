package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;
/**
 * 
 * @author zhangqi
 *
 */
public class DriverDataStub implements DriverDataService{
	@Override
	public ResultMessage insert(DriverPO dpo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	@Override
	public DriverPO find(String id) throws RemoteException{
		return new DriverPO("001A001","李林","19800406","025001001","18339409234",Sex.MALE,"20170930");
	}
	@Override
	public ResultMessage delete(String id) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	@Override
	public ResultMessage update(DriverPO dpo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	@Override
	public ResultMessage init() throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	@Override
	public ArrayList<DriverPO> findAll() throws RemoteException{
		ArrayList<DriverPO> array=new ArrayList<DriverPO>();
		array.add(new DriverPO("001A001","李林","19800406","025001001","18339409234",Sex.MALE,"20170930"));
		return array;
	}
}
