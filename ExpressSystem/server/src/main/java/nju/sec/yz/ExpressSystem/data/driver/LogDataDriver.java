package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;

public class LogDataDriver {
	public void drive(LogDataService lds) throws RemoteException
	{
		if(lds.find("")!=null)
			System.out.println("#log find SUCCESS");
		if(!lds.findAll().isEmpty())
			System.out.println("#log find all SUCCESS");
		
			System.out.println("#log insert SUCCESS");
	}
}
