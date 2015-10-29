package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;

public class OutDataDriver {
	public void drive(OutDataService ods) throws RemoteException
	{
		if(ods.find("")!=null)
			System.out.println("#out data find SUCCESS");
		if(!ods.findAll().isEmpty())
			System.out.println("#out data find all SUCCESS");
		if(ods.delete("")==ResultMessage.SUCCESS)
			System.out.println("#out data delete SUCCESS");
		if(ods.init()==ResultMessage.SUCCESS)
			System.out.println("#out data delete SUCCESS");
		if(ods.insert(null)==ResultMessage.SUCCESS)
		System.out.println("#out data insert SUCCESS");
		if(ods.update(null)==ResultMessage.SUCCESS)
		System.out.println("#out data update SUCCESS");
		
	}
}
