package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.po.AgencyPO;


public class AgencyDataDriver {
	public void drive(AgencyDataService ads) throws RemoteException
	{
		if(ads.init()==ResultMessage.SUCCESS)
			System.out.println("#agency initialize SUCCESS");
		if(ads.insert(null)==ResultMessage.SUCCESS)
			System.out.println("#agency insert SUCCESS");
		if(ads.find(null)!=null)
			System.out.println("#agency find SUCCESS");
		if(!ads.findAll().isEmpty())
			System.out.println("#agency find all SUCCESS");
		if(ads.delete(null)==ResultMessage.SUCCESS)
			System.out.println("#agency delete SUCCESS");
		if(ads.update(null)==ResultMessage.SUCCESS)
			System.out.println("#agency update SUCCESS");
		
		
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
}

/**
 * 
 * @author zhangqi
 *
 */

//	public static void main(String[] args) throws RemoteException {
//		AgencyDataService ads=new AgencyDataStub();
//		AgencyDataDriver add=new AgencyDataDriver();
//		add.drive(ads);
//	}


