package nju.sec.yz.ExpressSystem.bl.stub;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ConstBlStub implements ConstBlService {

	@Override
	public ResultMessage modifyCity(CityVO cv) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CityVO observeCity(String beginPlace,String endPlace) {
		// TODO 自动生成的方法存根
		return new CityVO(new CityInformation("hh","jj:","jj","jj",1000));
	}

	@Override
	public ResultMessage addCity(CityVO cp)  {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage deleteCity(String beginPlace, String endPlace) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override

	public ResultMessage modifyPrice(PriceVO pp)  {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public PriceVO observePrize()  {
		// TODO 自动生成的方法存根
		PriceVO price=new PriceVO();
		price.setPriceInformation(new PriceInformation());
		return price;
	}

	@Override
	public List<String> getCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityVO> observeAllCity() {
		// TODO Auto-generated method stub
		return null;
	}
}
