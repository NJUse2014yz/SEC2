package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;
/**
 * @author YU Fan
 */
import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.SalaryPO;

import nju.sec.yz.ExpressSystem.common.ResultMessage;

public interface SalaryDataService {
	public ArrayList<SalaryPO> findAll() throws RemoteException;
	public ResultMessage update(SalaryPO sp) throws RemoteException;
}
