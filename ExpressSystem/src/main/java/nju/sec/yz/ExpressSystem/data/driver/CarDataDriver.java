package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.CarDataStub;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
/**
 * 
 * @author zhangqi
 *
 */
public class CarDataDriver {
	public void drive(CarDataService cds) throws RemoteException{
		ResultMessage result=cds.delete(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("delete");
		}
		
		result=cds.init();
		if(result==ResultMessage.SUCCESS){
			System.out.println("init");
		}
		
		result=cds.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("insert");
		}
		
		result=cds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update");
		}
		
		CarPO cpo=cds.find(null);
			System.out.println(cpo.getId());
			
		ArrayList<CarPO> array=cds.findAll();
		System.out.println(((array.get(1)).getNumber()));
		
		
	}
	
	
	
//	public static void main(String[] args) throws RemoteException {
//		CarDataService cds=new CarDataStub();
//		CarDataDriver cdd=new CarDataDriver();
//		cdd.drive(cds);
//	}
}
