package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.AgencyDataStub;
import nju.sec.yz.ExpressSystem.data.stub.StaffDataStub;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;
import nju.sec.yz.ExpressSystem.po.StaffPO;

/**
 * 
 * @author zhangqi
 *
 */
public class StaffDataDriver {
	public void drive(StaffDataService sds)throws RemoteException{
		ResultMessage result=sds.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("Insert!");
		}
		
		result=sds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update!");
		}
		
		result=sds.delete(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("delete!");
		}
		
		result=sds.init();
		if(result==ResultMessage.SUCCESS){
			System.out.println("init!");
		}
		
		StaffPO spo=(StaffPO) sds.find(null);
		System.out.println(spo.getName());
		
		ArrayList<StaffPO> array=sds.findAll();
		System.out.println(array.size());
	}
	
	
//	public static void main(String[] args)throws RemoteException {
//		StaffDataService sds=new StaffDataStub();
//		StaffDataDriver sdd=new StaffDataDriver();
//		sdd.drive(sds);
//	}
}
