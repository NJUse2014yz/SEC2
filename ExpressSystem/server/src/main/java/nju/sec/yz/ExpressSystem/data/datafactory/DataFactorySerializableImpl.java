package nju.sec.yz.ExpressSystem.data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.sec.yz.ExpressSystem.data.carAndDriverdata.CarDataImpl;
import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataImpl;
import nju.sec.yz.ExpressSystem.data.deliverdata.DeliverDataImpl;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDataService getAccountDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InDataService getInDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDataService getOutDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDataService getLogDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDataService getAgencyDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstDataService getConstDataService() throws RemoteException {
		return new ConstDataStub();
	}

	@Override
	public SalaryDataService getSalaryDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDataService getStaffDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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

}
