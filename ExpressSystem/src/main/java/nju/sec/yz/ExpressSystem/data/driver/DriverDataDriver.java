package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.DriverDataStub;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

/**
 * 
 * @author zhangqi
 *
 */
public class DriverDataDriver {
	public void drive(DriverDataService dds) throws RemoteException{
		ResultMessage result=dds.delete(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("delete");
		}
		
		result=dds.init();
		if(result==ResultMessage.SUCCESS){
			System.out.println("init");
		}
		
		result=dds.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("insert");
		}
		
		result=dds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update");
		}
		
		DriverPO dpo=dds.find(null);
				System.out.println(dpo.getLicenseDeadLine());
				
		ArrayList<DriverPO> array= dds.findAll();
		System.out.println(array.get(0).getName());
				
	}
	
	public static void main(String[] args) throws RemoteException {
		DriverDataService dds=new DriverDataStub();
		DriverDataDriver ddd=new DriverDataDriver();
		ddd.drive(dds);
	}
}
