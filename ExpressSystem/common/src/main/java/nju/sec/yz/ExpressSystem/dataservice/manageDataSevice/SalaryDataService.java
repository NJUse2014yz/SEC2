package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;
import java.rmi.Remote;
/**
 * @author YU Fan
 */
import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.SalaryPO;

import nju.sec.yz.ExpressSystem.common.ResultMessage;

public interface SalaryDataService extends Remote{
	public ArrayList<SalaryPO> findAll() throws RemoteException;
	public ResultMessage update(SalaryPO sp) throws RemoteException;
}
