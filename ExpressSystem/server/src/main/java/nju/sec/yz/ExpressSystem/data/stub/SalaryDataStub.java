package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.SalaryPO;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;

public class SalaryDataStub implements SalaryDataService{

	@Override
	public ArrayList<SalaryPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		return new ArrayList<SalaryPO>();
	}

	@Override
	public ResultMessage update(SalaryPO sp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

}
