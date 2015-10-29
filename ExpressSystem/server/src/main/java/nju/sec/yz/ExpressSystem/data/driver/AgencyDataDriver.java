package nju.sec.yz.ExpressSystem.data.driver;
/**
 * @author YU Fan
 */
import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;

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
	}
//	public static void main(String[] args) throws RemoteException {
//		AgencyDataService ads=new AgencyDataStub();
//		AgencyDataDriver add=new AgencyDataDriver();
//		add.drive(ads);
//	}
}