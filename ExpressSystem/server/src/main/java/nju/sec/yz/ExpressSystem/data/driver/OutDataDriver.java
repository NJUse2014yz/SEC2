package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;

public class OutDataDriver {
	public void drive(OutDataService ods) throws RemoteException
	{
		
		if(!ods.findAll().isEmpty())
			System.out.println("#out data find all SUCCESS");
		
			System.out.println("#out data delete SUCCESS");
		
			System.out.println("#out data delete SUCCESS");
		
		System.out.println("#out data insert SUCCESS");
		
		System.out.println("#out data update SUCCESS");
		
	}
}
