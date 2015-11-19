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
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
/**
 * 获得data引用
 * @author 周聪
 *
 */
public interface DatafactoryService extends Remote{

	public OrderDataService getOrderDataService() throws RemoteException;
	
	public ReceiptCounterDataService getCounterDataService() throws RemoteException;
	
	public DeliverDataService getDeliverDataService() throws RemoteException;
	
	public AccountBookDataService getAccountBookDataService() throws RemoteException;
	
	public AccountDataService getAccountDataService() throws RemoteException;
	
	public InDataService getInDataService() throws RemoteException;
	
	public OutDataService getOutDataService() throws RemoteException;
	
	public CarDataService getCarDataService() throws RemoteException;
	
	public DriverDataService getDriverDataService() throws RemoteException;
	
	public InventoryDataService getInventoryDataService() throws RemoteException;
	
	public LogDataService getLogDataService() throws RemoteException;
	
	public AgencyDataService getAgencyDataService() throws RemoteException;
	
	public ConstDataService getConstDataService() throws RemoteException;
	
	public SalaryDataService getSalaryDataService() throws RemoteException;
	
	public StaffDataService getStaffDataService() throws RemoteException;
	
	public ReceiptDataService getReceiptDataService() throws RemoteException;
	
	public UserDataService getUserDataService() throws RemoteException;
	
}
