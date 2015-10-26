package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.DeliverDataStub;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;

/**
 * 
 * @author zhangqi
 *
 */
public class DeliverDataDriver {

	public void drive(DeliverDataService dds) throws RemoteException{
		ResultMessage result=dds.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("insert");
		}
		
		result=dds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update");
		}
		
		DeliverPO dpo=dds.find();
		System.out.println(dpo);
		
		ArrayList<DeliverPO> array=dds.findAll();
		System.out.println(array.size());
				
	}
	
//	public static void main(String[] args) throws RemoteException {
//		DeliverDataService dds=new DeliverDataStub();
//		DeliverDataDriver ddd=new DeliverDataDriver();
//		ddd.drive(dds);
//		
//		}
}
