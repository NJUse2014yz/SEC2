package nju.sec.yz.ExpressSystem.data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import nju.sec.yz.ExpressSystem.data.deliverdata.DeliverDataImpl;
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
	public AccountBookDataService getAccountBookDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDataService getAccountDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InDataService getInDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDataService getOutDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDataService getCarDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDataService getDriverDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryDataService getInventoryDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDataService getLogDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDataService getAgencyDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstDataService getConstDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalaryDataService getSalaryDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDataService getStaffDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptDataService getReceiptDataService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDataService getUserDataService() {
		// TODO Auto-generated method stub
		return null;
	}

}
