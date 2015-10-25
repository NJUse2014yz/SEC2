package nju.sec.yz.ExpressSystem.bl.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PrizeVO;

public class ConstBlStub implements ConstBlService {

	@Override
	public ResultMessage update(CityVO cv) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<CityVO> findAllCity() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage insert(CityVO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage delete(String beginPlace, String endPlace)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(PrizeVO pp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<PrizeVO> findAllPrize() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}


}
