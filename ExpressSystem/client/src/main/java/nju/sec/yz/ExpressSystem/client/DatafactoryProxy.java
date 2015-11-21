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
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
/**
 * datafactory在客户端的代理
 * @author 周聪
 *
 */
public class DatafactoryProxy {

	private static DatafactoryService datafactory;
	
	public static CityIdDataService getCityIdDataService() throws RemoteException {

		return datafactory.getCityIdDataService();
	}
	
	static public ReceiptCounterDataService getCounterDataService() throws RemoteException {
		return datafactory.getCounterDataService();
	}
	
	static public DeliverDataService getDeliverDataService() throws RemoteException{
		return datafactory.getDeliverDataService();
	}

	
	static public AccountBookDataService getAccountBookDataService()  throws RemoteException{
		return datafactory.getAccountBookDataService();
	}

	public static AccountDataService getAccountDataService()  throws RemoteException{
		return datafactory.getAccountDataService();
	}

	
	static public InDataService getInDataService()  throws RemoteException{
		return datafactory.getInDataService();
	}

	
	static public OutDataService getOutDataService()  throws RemoteException{
		return datafactory.getOutDataService();
	}

	
	static public CarDataService getCarDataService()  throws RemoteException{
		return datafactory.getCarDataService();
	}

	
	static public DriverDataService getDriverDataService()  throws RemoteException{
		return datafactory.getDriverDataService();
	}

	
	static public InventoryDataService getInventoryDataService()  throws RemoteException{
		return datafactory.getInventoryDataService();
	}

	
	static public LogDataService getLogDataService()  throws RemoteException{
		return datafactory.getLogDataService();
	}

	
	static public AgencyDataService getAgencyDataService()  throws RemoteException{
		return datafactory.getAgencyDataService();
	}

	
	static public ConstDataService getConstDataService()  throws RemoteException{
		return datafactory.getConstDataService();
	}

	
	static public SalaryDataService getSalaryDataService()  throws RemoteException{
		return datafactory.getSalaryDataService();
	}

	
	static public StaffDataService getStaffDataService()  throws RemoteException{
		return datafactory.getStaffDataService();
	}

	
	static public ReceiptDataService getReceiptDataService()  throws RemoteException{
		return datafactory.getReceiptDataService();
	}

	
	static public UserDataService getUserDataService()  throws RemoteException{
		return datafactory.getUserDataService();
	}


	public static void setDatafactory(DatafactoryService datafactory) {
		DatafactoryProxy.datafactory = datafactory;
	}

	public static OrderDataService getOrderDataService() throws RemoteException{
		
		return datafactory.getOrderDataService();
	}

}
