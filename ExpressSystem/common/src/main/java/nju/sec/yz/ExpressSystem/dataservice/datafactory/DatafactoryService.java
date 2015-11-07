package nju.sec.yz.ExpressSystem.dataservice.datafactory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
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
 * 获得data引用
 * @author 周聪
 *
 */
public interface DatafactoryService extends Remote{

	public DeliverDataService getDeliverDataService() throws RemoteException;
	
//	public AccountBookDataService getAccountBookDataService();
//	
//	public AccountDataService getAccountDataService();
//	
//	public InDataService getInDataService();
//	
//	public OutDataService getOutDataService();
//	
//	public CarDataService getCarDataService();
//	
//	public DriverDataService getDriverDataService();
//	
//	public InventoryDataService getInventoryDataService();
//	
//	public LogDataService getLogDataService();
//	
//	public AgencyDataService getAgencyDataService();
//	
//	public ConstDataService getConstDataService();
//	
//	public SalaryDataService getSalaryDataService();
//	
//	public StaffDataService getStaffDataService();
//	
//	public ReceiptDataService getReceiptDataService();
//	
//	public UserDataService getUserDataService();
	
}
