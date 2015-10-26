package nju.sec.yz.ExpressSystem.bl.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ConstBlStub implements ConstBlService {

	@Override
	public ResultMessage modifyCity(CityVO cv) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public CityVO observeCity(String beginPlace,String endPlace) throws RemoteException {
		// TODO 自动生成的方法存根
		return new CityVO();
	}

	@Override
	public ResultMessage addCity(CityVO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteCity(String beginPlace, String endPlace)
			throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override

	public ResultMessage modifyPrice(PriceVO pp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public PriceVO observePrize() throws RemoteException {
		// TODO 自动生成的方法存根
		return new PriceVO();
	}
}
