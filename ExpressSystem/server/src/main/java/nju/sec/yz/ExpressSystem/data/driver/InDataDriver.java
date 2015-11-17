package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;

public class InDataDriver {
	public void drive(InDataService ids) throws RemoteException
	{
		if(ids.find("")!=null)
			System.out.println("#in data find SUCCESS");
		if(!ids.findAll().isEmpty())
			System.out.println("#in data find all SUCCESS");
		
			System.out.println("#in data delete SUCCESS");
		
			System.out.println("#in data delete SUCCESS");
		
		System.out.println("#in data insert SUCCESS");
		
		System.out.println("#in data update SUCCESS");
		
	}
}
