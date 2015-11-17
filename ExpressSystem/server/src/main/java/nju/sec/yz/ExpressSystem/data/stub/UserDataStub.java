package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.po.UserPO;
/**
 * 
 * @author zhangqi
 *
 */
public class UserDataStub extends UnicastRemoteObject implements UserDataService {
	@Override
	public ResultMessage insert(UserPO upo) throws RemoteException{
		return null;
	}
	
	@Override
	public UserPO find(String id) throws RemoteException{
		return new UserPO("E001","王明","E001",Status.JUNIOR_ACCOUNTANCY);
	}
	
	@Override
	public ResultMessage delete(String id) throws RemoteException{
		return null;
	}
	
	@Override
	public ResultMessage update(UserPO upo) throws RemoteException{
		return null;
	}
	
	@Override
	public ResultMessage init() throws RemoteException{
		return null;
	}
	
	@Override
	public ArrayList<UserPO> findAll() throws RemoteException{
		ArrayList<UserPO> array=new ArrayList<UserPO>();
		array.add(new UserPO("E001","王明","E001",Status.JUNIOR_ACCOUNTANCY));
		array.add(new UserPO("S001","陈后","S001",Status.MANAGER));
		return array;
	}	
}
