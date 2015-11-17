package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.UserDataStub;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.po.UserPO;
/**
 * 
 * @author zhangqi
 *
 */
public class UserDataDriver {
	public void drive(UserDataService uds) throws RemoteException{
		ResultMessage result=uds.insert(new UserPO("E001","王明","E001",Status.JUNIOR_ACCOUNTANCY));
		if(result==ResultMessage.SUCCESS){
			System.out.println("Insert!");
		}
		
		result=uds.delete("E0001");
		if(result==ResultMessage.SUCCESS){
			System.out.println("delete!");
		}
		
		result=uds.update(new UserPO("E001","王明","E001",Status.JUNIOR_ACCOUNTANCY));
		if(result==ResultMessage.SUCCESS){
			System.out.println("update!");
		}
		
		
		UserPO upo = uds.find("E001");
		if (("E001" == upo.getId()) && ("王明" == upo.getName()) && ("E001" == upo.getPassword())
				&& (Status.JUNIOR_ACCOUNTANCY == upo.getPower())) {
			System.out.println("find!");
		}
		
		ArrayList<UserPO> array=uds.findAll();
		System.out.println(array.get(0).getName());
		
		
		
	}
	
//	public static void main(String[] args) throws RemoteException {
//		UserDataService uds=new UserDataStub();
//		UserDataDriver sdd=new UserDataDriver();
//		sdd.drive(uds);
//	}

	
}


	