package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.data.stub.AccountBookStub;
import nju.sec.yz.ExpressSystem.data.stub.AccountDataStub;
import nju.sec.yz.ExpressSystem.data.stub.AgencyDataStub;
import nju.sec.yz.ExpressSystem.data.stub.ConstDataStub;
import nju.sec.yz.ExpressSystem.data.stub.LogDataStub;
import nju.sec.yz.ExpressSystem.data.stub.OutDataStub;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;

public class Driver {
	public static void main(String[] args) throws RemoteException
	{
		AccountBookDataDriver abdd=new AccountBookDataDriver();
		AccountBookDataService abds=new AccountBookStub();
		abdd.drive(abds);
		AccountDataDriver add1=new AccountDataDriver();
		AccountDataService ads1=new AccountDataStub();
		add1.drive(ads1);
		ConstDataDriver cdd=new ConstDataDriver();
		ConstDataService cds=new ConstDataStub();
		cdd.drive(cds);
		
		OutDataDriver odd=new OutDataDriver();
		OutDataService ods=new OutDataStub();
		odd.drive(ods);		
		LogDataDriver ldd=new LogDataDriver();
		LogDataService lds=new LogDataStub();
		ldd.drive(lds);
		AgencyDataDriver add=new AgencyDataDriver();
		AgencyDataService ads=new AgencyDataStub();
		add.drive(ads);
	}
}
