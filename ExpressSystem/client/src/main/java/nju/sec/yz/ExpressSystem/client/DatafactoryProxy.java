package nju.sec.yz.ExpressSystem.client;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.dataservice.datafactory.DatafactoryService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
/**
 * datafactory在客户端的代理
 * @author 周聪
 *
 */
public class DatafactoryProxy {

	private static DatafactoryService datafactory;
	
	
	static public DeliverDataService getDeliverDataService() throws RemoteException{
		return datafactory.getDeliverDataService();
	}

	
	static public AccountBookDataService getAccountBookDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	static AccountDataService getAccountDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public InDataService getInDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public OutDataService getOutDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public CarDataService getCarDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public DriverDataService getDriverDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public InventoryDataService getInventoryDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public LogDataService getLogDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public AgencyDataService getAgencyDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public ConstDataService getConstDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public SalaryDataService getSalaryDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public StaffDataService getStaffDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	
	static public ReceiptDataService getReceiptDataService()  throws RemoteException{
		
		return datafactory.getReceiptDataService();
	}

	
	static public UserDataService getUserDataService()  throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}


	public static void setDatafactory(DatafactoryService datafactory) {
		DatafactoryProxy.datafactory = datafactory;
	}

}
