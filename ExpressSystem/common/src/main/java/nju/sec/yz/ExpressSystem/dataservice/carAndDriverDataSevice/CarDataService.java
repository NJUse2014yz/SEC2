package nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CarPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface CarDataService extends Remote{

	public ResultMessage insert(CarPO cpo) throws RemoteException;

	public CarPO find(String id) throws RemoteException;

	public ResultMessage delete(String id) throws RemoteException;

	public ResultMessage update(CarPO cpo) throws RemoteException;

	public ResultMessage init(List<CarPO> cars) throws RemoteException;

	/**
	 * 查询本营业厅的车辆
	 * @param positionId
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<CarPO> findAll(String positionId) throws RemoteException;
}
