package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;

public class ConstDataDriver {
	public void drive(ConstDataService cds) throws RemoteException
	{
		if(cds.delete("", "")==ResultMessage.SUCCESS)
			System.out.println("#const data delete SUCCESS");
		if(!cds.findAllCity().isEmpty())
			System.out.println("#const data find all city SUCCESS");
		if(cds.insert(null)==ResultMessage.SUCCESS)
			System.out.println("#const data insert SUCCESS");
		if(cds.updateCity(null)==ResultMessage.SUCCESS)
			System.out.println("#const data update city SUCCESS");
		if(cds.updatePrice(null)==ResultMessage.SUCCESS)
			System.out.println("#const data update price SUCCESS");
		if(!cds.findAllPrice().isEmpty())
			System.out.println("#const data find all price SUCCESS");
	}
}
