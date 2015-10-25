package nju.sec.yz.ExpressSystem.dataservice.userDataSevice;
/**
 * @Author YU Fan
*/
import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.UserPO;
public interface UserDataService 
{
	public ResultMessage insert(UserPO upo) throws RemoteException;
	public UserPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(UserPO upo) throws RemoteException;
	public ResultMessage init() throws RemoteException;
	public ArrayList<UserPO> findAll() throws RemoteException;	
}
