package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.po.UserPO;
/**
 * 
 * @author zhangqi
 *
 */
public class UserDataStub implements UserDataService {
	@Override
	public ResultMessage insert(UserPO upo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public UserPO find(String id) throws RemoteException{
		return new UserPO("E001","王明","E001",Power.JUNIOR_ACCOUNTANCY);
	}
	
	@Override
	public ResultMessage delete(String id) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public ResultMessage update(UserPO upo) throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public ResultMessage init() throws RemoteException{
		return ResultMessage.SUCCESS;
	}
	
	@Override
	public ArrayList<UserPO> findAll() throws RemoteException{
		ArrayList<UserPO> array=new ArrayList<UserPO>();
		array.add(new UserPO("E001","王明","E001",Power.JUNIOR_ACCOUNTANCY));
		array.add(new UserPO("S001","陈后","S001",Power.MANAGER));
		return array;
	}	
}
