package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 
 * @author zhangqi
 *
 */
public class ReceiptDataDriver {
	public void drive(ReceiptDataService rds) throws RemoteException{
	
		ResultMessage result=rds.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update");
		}
		
		result=rds.insert(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("insert");
		}
		
		ReceiptPO rpo=rds.find(null);
		System.out.println(rpo);
		
		ArrayList<ReceiptPO> array=rds.findByType(null);
		System.out.println(array.size());
		
	}
	
//	public static void main(String[] args) throws RemoteException {
//		ReceiptDataService rds=new ReceiptDataStub();
//		ReceiptDataDriver rdd=new ReceiptDataDriver();
//		rdd.drive(rds);
}
