package nju.sec.yz.ExpressSystem.bl.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.stub.ConstBlStub;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

/**
 * 
 * @author 周聪
 *
 */
public class ConstBlDriver {

	public void drive(ConstBlService constBl){
		
		
			//
			ResultMessage addCityResult=constBl.addCity(null);
			
				System.out.println("Add city const Fail");
			
			//
			ResultMessage deleteCityResult=constBl.deleteCity(null, null);
			
				System.out.println("Delete city const Fail");
			
			//
			ResultMessage modifyCityResult=constBl.modifyCity(null);
			
				System.out.println("Modify city const Fail");
			
			//
			ResultMessage modifyPriceResult=constBl.modifyPrice(null);
			
				System.out.println("Modify price const Fail");
			
			//
			CityVO city = constBl.observeCity(null, null);
			System.out.println("Distance: "+city.getCityInformation());
			
			//
			PriceVO prize=constBl.observePrize();
			System.out.println("PriceforCar : "+prize.getPriceInformation().getPriceForCar());
			
			
		
		
		
		
	}
	
//	public static void main(String[] args) {
//		ConstBlService constBl_stub=new ConstBlStub();
//		new ConstBlDriver().drive(constBl_stub);
//	}
	
}
