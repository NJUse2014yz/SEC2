package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.AgencyDataStub;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;

/**
 * 
 * @author zhangqi
 *
 */
public class AgencyDataDriver {
	public void drive(AgencyDataService ads) throws RemoteException{
		ResultMessage result=ads.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("Insert!");
		}
		
		result=ads.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update!");
		}
		
		result=ads.delete(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("delete!");
		}
		
		result=ads.init();
		if(result==ResultMessage.SUCCESS){
			System.out.println("init!");
		}
		
		AgencyPO apo=ads.find(null);
		System.out.println(apo.getName());
		
		ArrayList<AgencyPO> array=ads.findAll();
		System.out.println(array.size());
	}
	
//	public static void main(String[] args) throws RemoteException {
//		AgencyDataService ads=new AgencyDataStub();
//		AgencyDataDriver add=new AgencyDataDriver();
//		add.drive(ads);
//	}
}
