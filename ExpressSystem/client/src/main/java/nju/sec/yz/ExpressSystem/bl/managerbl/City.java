package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.po.CityIdPO;
import nju.sec.yz.ExpressSystem.vo.CityIdVO;
import nju.sec.yz.ExpressSystem.vo.CityVO;

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
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	@Override
	public List<CityIdVO> getCityList() {
		List<CityIdPO> pos=null;
		List<CityIdVO> vos=new ArrayList<>();
		try {
			pos=data.getAll();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		
		for(CityIdPO po:pos){
			CityIdVO vo=new CityIdVO(po.getName(), po.getName());
			vos.add(vo);
		}
		
		return vos;
	}
	
	public List<String> getCities(){
		
		List<CityIdPO> pos=null;
		List<String> cities=new ArrayList<>();
		try {
			pos=data.getAll();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		
		for(CityIdPO po:pos){
			
			cities.add(po.getName());
		}
		
		return cities;
	}
	
	public void addCity(String name,String id){
		CityIdPO po=new CityIdPO(name, id);
		try {
			data.addCity(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	
	
	
}
