package nju.sec.yz.ExpressSystem.data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.sec.yz.ExpressSystem.data.accountdata.AccountBookDataImpl;
import nju.sec.yz.ExpressSystem.data.accountdata.AccountDataImpl;
import nju.sec.yz.ExpressSystem.data.accountdata.InDataImpl;
import nju.sec.yz.ExpressSystem.data.accountdata.OutDataImpl;
import nju.sec.yz.ExpressSystem.data.carAndDriverdata.CarDataImpl;
import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataImpl;
import nju.sec.yz.ExpressSystem.data.deliverdata.DeliverDataImpl;
import nju.sec.yz.ExpressSystem.data.deliverdata.OrderDataImpl;
import nju.sec.yz.ExpressSystem.data.inventorydata.InventoryDataImpl;
import nju.sec.yz.ExpressSystem.data.logdata.LogDataImpl;
import nju.sec.yz.ExpressSystem.data.managedata.AgencyDataImpl;
import nju.sec.yz.ExpressSystem.data.managedata.CityIdDataImpl;
import nju.sec.yz.ExpressSystem.data.managedata.SalaryDataImpl;
import nju.sec.yz.ExpressSystem.data.managedata.StaffDataImpl;
import nju.sec.yz.ExpressSystem.data.receiptdata.ReceiptCounterDataImpl;
import nju.sec.yz.ExpressSystem.data.receiptdata.ReceiptDataImpl;
import nju.sec.yz.ExpressSystem.data.stub.ConstDataStub;
import nju.sec.yz.ExpressSystem.data.stub.UserDataStub;
import nju.sec.yz.ExpressSystem.data.userdata.UserDataImpl;
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
 * 
 * @author 周聪
 *
 */
public class DataFactorySerializableImpl extends UnicastRemoteObject implements DatafactoryService{

	public DataFactorySerializableImpl() throws RemoteException {
		super();
	}

	@Override
	public DeliverDataService getDeliverDataService() throws RemoteException{
		return new DeliverDataImpl();
	}

	@Override
	public AccountBookDataService getAccountBookDataService() throws RemoteException {
		
		return new AccountBookDataImpl();
	}

	@Override
	public AccountDataService getAccountDataService() throws RemoteException {
		
		return new AccountDataImpl();
	}

	@Override
	public InDataService getInDataService() throws RemoteException {
		
		return new InDataImpl();
	}

	@Override
	public OutDataService getOutDataService() throws RemoteException {
		
		return new OutDataImpl();
	}

	@Override
	public CarDataService getCarDataService() throws RemoteException {
		
		return new CarDataImpl();
	}

	@Override
	public DriverDataService getDriverDataService() throws RemoteException {

		return new DriverDataImpl();
	}

	@Override
	public InventoryDataService getInventoryDataService() throws RemoteException {
		
		return new InventoryDataImpl();
	}

	@Override
	public LogDataService getLogDataService() throws RemoteException {
		
		return new LogDataImpl();
	}

	@Override
	public AgencyDataService getAgencyDataService() throws RemoteException {
		
		return new AgencyDataImpl();
	}

	@Override
	public ConstDataService getConstDataService() throws RemoteException {
		return new ConstDataStub();
	}

	@Override
	public SalaryDataService getSalaryDataService() throws RemoteException {
		
		return new SalaryDataImpl();
	}

	@Override
	public StaffDataService getStaffDataService() throws RemoteException {
		
		return new StaffDataImpl();
	}

	@Override
	public ReceiptDataService getReceiptDataService() throws RemoteException {
		return new ReceiptDataImpl();
	}

	@Override
	public UserDataService getUserDataService() throws RemoteException {
		return new UserDataImpl();
	}

	@Override
	public ReceiptCounterDataService getCounterDataService() throws RemoteException {
		return new ReceiptCounterDataImpl();
	}

	@Override
	public OrderDataService getOrderDataService() throws RemoteException {
		return new OrderDataImpl();
	}

	@Override
	public CityIdDataService getCityIdDataService() throws RemoteException {
		
		return new CityIdDataImpl();
	}

}
