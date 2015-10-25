package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PrizePO;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;

public class ConstDataStub implements ConstDataService{

	@Override
	public ResultMessage update(CityPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CityPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		return new ArrayList<CityPO>();
	}

	@Override
	public ResultMessage insert(CityPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(String beginPlace, String endPlace)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(PrizePO pp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
