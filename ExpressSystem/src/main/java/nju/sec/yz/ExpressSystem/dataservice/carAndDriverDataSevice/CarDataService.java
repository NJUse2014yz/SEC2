package nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CarPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface CarDataService {

	public ResultMessage insert(CarPO cpo) throws RemoteException;

	public CarPO find(String id) throws RemoteException;

	public ResultMessage delete(String id) throws RemoteException;

	public ResultMessage update(CarPO cpo) throws RemoteException;

	public ResultMessage init() throws RemoteException;

	public ArrayList<CarPO> findAll() throws RemoteException;
}
