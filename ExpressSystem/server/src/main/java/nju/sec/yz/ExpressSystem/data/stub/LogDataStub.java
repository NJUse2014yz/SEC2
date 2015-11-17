package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.po.LogPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class LogDataStub implements LogDataService {
	@Override
	public ResultMessage insert(LogPO lpo) throws RemoteException{
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public LogPO find(String time) throws RemoteException {
		return new LogPO("20151024", "init the information of drivers", "001C002");
	}

	@Override
	public ArrayList<LogPO> findAll() throws RemoteException {
		ArrayList<LogPO> array = new ArrayList<LogPO>();
		array.add(new LogPO("20151024", "init the information of drivers", "001C002"));
		return array;

	}
}
