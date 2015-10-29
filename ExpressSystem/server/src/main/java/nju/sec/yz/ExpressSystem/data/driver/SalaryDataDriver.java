package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.SalaryDataStub;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.po.SalaryPO;

/**
 * 
 * @author zhangqi
 *
 */
public class SalaryDataDriver {
	public void drive(SalaryDataService sds) throws RemoteException{
		ResultMessage result=sds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update!");
		}
		
		ArrayList<SalaryPO> array =sds.findAll();
		System.out.println(array.size());
	}
	
//	public static void main(String[] args) throws RemoteException {
//		SalaryDataService sds =new SalaryDataStub();
//		SalaryDataDriver sdd=new SalaryDataDriver();
//		sdd.drive(sds);
//	}
}
