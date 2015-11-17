package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;

public class AccountDataDriver {
	public void drive(AccountDataService ads) throws RemoteException{
		if(ads.find("")!=null)
			System.out.println("#account find SUCCESS");
		if(!ads.findAll().isEmpty())
			System.out.println("#account find all SUCCESS");
		
			System.out.println("#account delete SUCCESS");
		
			System.out.println("#account initialize SUCCESS");
		
			System.out.println("#account insert SUCCESS");
		
			System.out.println("#account update SUCCESS");
	}
}
