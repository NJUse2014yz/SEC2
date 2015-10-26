package nju.sec.yz.ExpressSystem.bl.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ConstBlStub implements ConstBlService {

	@Override
	public ResultMessage updateCity(CityVO cv) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CityVO> findAllCity() throws RemoteException {
		// TODO 自动生成的方法存根
		return new ArrayList<CityVO>();
	}

	@Override
	public ResultMessage insert(CityVO cp) throws RemoteException {
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

	public ResultMessage updatePrice(PriceVO pp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PriceVO> findAllPrize() throws RemoteException {
		// TODO 自动生成的方法存根
		return new ArrayList<PriceVO>();
	}
}
