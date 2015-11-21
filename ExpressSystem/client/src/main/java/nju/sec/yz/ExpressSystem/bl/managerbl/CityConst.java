package nju.sec.yz.ExpressSystem.bl.managerbl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;


/**
 * 城市距离常量信息的领域模型对象
 * @author 周聪
 */
public class CityConst implements CityDistanceService {
	public ResultMessage modifyCity(CityVO cv) {
		// TODO Auto-generated method stub
		return null;
	}

	public CityVO observeCity(String beginPlace, String endPlace) {
		
		return null;
	}

	
	public ResultMessage addCity(CityVO cp) {
		//保存城市常量
		
		
		//保存城市
		
		return null;
	}

	/**
	 * 保存城市
	 */
	public void saveCity(){
		City city=new City();
		//TODO
		city.addCity();
	}
	
	public ResultMessage deleteCity(String beginPlace, String endPlace)  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistance(String beginPlace, String endPlace) {
		// TODO Auto-generated method stub
		return 1000;
	}

}
