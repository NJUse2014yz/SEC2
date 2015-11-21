package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.vo.CityIdVO;

/**
 * 维护所有城市名称和编号
 * @author 周聪
 *
 */
public class City implements CityListService{
	
	private CityIdDataService data;
	
	public City() {
		try {
			data=DatafactoryProxy.getCityIdDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CityIdVO> getCityList() {
		
		return null;
	}
	
	public List<String> getCities(){
		//stub
		List<String> cities=new ArrayList<>();
		cities.add("南京");
		cities.add("北京");
		cities.add("上海");
		cities.add("广州");
		return cities;
	}
	
	public void addCity(String name,String id){
		
	}

	
	
	
}
