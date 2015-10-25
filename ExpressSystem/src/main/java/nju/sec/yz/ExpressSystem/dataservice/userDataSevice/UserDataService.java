package nju.sec.yz.ExpressSystem.dataservice.userDataSevice;
/**
 * @Author YU Fan
*/
import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.*;
import nju.sec.yz.ExpressSystem.ResultMessage;
import nju.sec.yz.ExpressSystem.po.UserP;

public interface UserDataService 
{
	public ResultMessage insert(UserP upo) throws RemoteException;
	public UserP find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(UserP upo) throws RemoteException;
	public ResultMessage init() throws RemoteException;
	public ArrayList<UserP> findAll() throws RemoteException;	
}
