package nju.sec.yz.ExpressSystem.dataservice.userDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;

public interface UserDataService 
{
	public ResultMessage insert(UserPO upo) throws RemoteException;
	public UserPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage update(UserPO upo) throws RemoteException;
	public ResultMessage init() throws RemoteException;
	public ArrayList<UserPO> findAll() throws RemoteException;	
}
