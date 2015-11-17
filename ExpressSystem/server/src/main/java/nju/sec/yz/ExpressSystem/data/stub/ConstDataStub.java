package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.po.PricePO;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;

public class ConstDataStub implements ConstDataService{

	@Override
	public ResultMessage updateCity(CityPO cp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CityPO> findAllCity() throws RemoteException {
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
	public ResultMessage updatePrice(PricePO pp) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PricePO> findAllPrice() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CityPO find(String beginPlace, String endPlace) throws RemoteException {
		return new CityPO(new CityInformation("北京","南京",1000));
	}

}
