package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.client.RMIHelper;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.po.CityPO;
import nju.sec.yz.ExpressSystem.vo.CityVO;


/**
 * 城市距离常量信息的领域模型对象
 * @author 周聪
 */
public class CityConst implements CityDistanceService {
	//营业厅间的距离为30km
	public static final double DISTANCE_OF_POSITION=30;
	
	private ConstDataService data;
	
	public CityConst() {
		try {
			data=DatafactoryProxy.getConstDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	public ResultMessage modifyCity(CityVO cv) {
		CityInformation info=cv.getCityInformation();
		CityInformation infoCopy=this.copyInfo(info);
		
		CityPO po=new CityPO(infoCopy);
		
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		try {
			message=data.updateCity(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return message;
	}

	public CityVO observeCity(String beginPlace, String endPlace) {
		CityVO vo=null; 
		try {
			CityPO po=data.find(beginPlace, endPlace);
			CityInformation info=po.getCityInformation();
			CityInformation infoCopy=this.copyInfo(info);
			vo=new CityVO(infoCopy);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public List<CityVO> getAllCity() {
		List<CityVO> cities=new ArrayList<>();
		System.out.println("city");
		try {
			List<CityPO> pos=data.findAllCity();
			System.out.println("citySize:"+pos.size());
			for(CityPO po:pos){
				CityInformation info=this.copyInfo(po.getCityInformation());
				CityVO city=new CityVO(info);
				cities.add(city);
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		System.out.println("citySize:"+cities.size());
		return cities;
	}

	
	public ResultMessage addCity(CityVO cp) {
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		//保存城市常量,往返两个方向都保存
		CityInformation info=cp.getCityInformation();
		CityInformation infoCopy=this.copyInfo(info);
		CityInformation infoCopy2=new CityInformation(info.getToCity(), info.getToID(), 
								info.getFromCity(), info.getToID(), info.getDistance());
		CityPO po=new CityPO(infoCopy);
		CityPO po2=new CityPO(infoCopy2);
		try {
			message=data.insert(po);
			if(message.getResult()==Result.FAIL)
				return message;
			message=data.insert(po2);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		//保存城市
		City city=new City();
		city.addCity(info.getFromCity(),info.getFromID());
		city.addCity(info.getToCity(), info.getToID());
		
		return message;
	}
	
	private CityInformation copyInfo(CityInformation info){
		CityInformation infoCopy=new CityInformation(info.getFromCity(), info.getFromID(), 
				info.getToCity(), info.getToID(), info.getDistance());
		return infoCopy;
	}

	
	public ResultMessage deleteCity(String beginPlace, String endPlace)  {
		ResultMessage message=new ResultMessage(Result.FAIL);
		try {
			message=data.delete(beginPlace, endPlace);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public double getDistance(String beginPlace, String endPlace) {
		//假设相同城市各营业厅与中转中心距离为30km
		if(beginPlace.equals(endPlace))
			return DISTANCE_OF_POSITION;
		
		if(beginPlace==null||endPlace==null)
			return 0;
		CityVO vo=this.observeCity(beginPlace, endPlace);
		
		double distanse=vo.getCityInformation().getDistance();
		
		return distanse;
	}

	
	
	

}
