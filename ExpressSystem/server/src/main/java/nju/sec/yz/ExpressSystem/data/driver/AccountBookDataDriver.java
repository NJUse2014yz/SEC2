package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;

public class AccountBookDataDriver {
	public void drive(AccountBookDataService abds) throws RemoteException
	{
		if(!abds.findAll().isEmpty())
			System.out.println("#account book find all SUCCESS");
		if(abds.init()!=null)
			System.out.println("#account book initialize SUCCESS");
		if(abds.find("")!=null)
			System.out.println("#account book insert SUCCESS");
		if(abds.delete(new AccountBookPO(null,null,null,null,null))==ResultMessage.SUCCESS)
			System.out.println("#account book delete SUCCESS");
		if(abds.update(new AccountBookPO(null,null,null,null,null))==ResultMessage.SUCCESS)
			System.out.println("#account book update SUCCESS");
		if(abds.insert(new AccountBookPO(null,null,null,null,null))==ResultMessage.SUCCESS)
			System.out.println("#account book insert SUCCESS");
	}
}
