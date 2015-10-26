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
		if(ads.delete("")==ResultMessage.SUCCESS)
			System.out.println("#account delete SUCCESS");
		if(ads.init()==ResultMessage.SUCCESS)
			System.out.println("#account initialize SUCCESS");
		if(ads.insert(null)==ResultMessage.SUCCESS)
			System.out.println("#account insert SUCCESS");
		if(ads.update(null)==ResultMessage.SUCCESS)
			System.out.println("#account update SUCCESS");
	}
}
