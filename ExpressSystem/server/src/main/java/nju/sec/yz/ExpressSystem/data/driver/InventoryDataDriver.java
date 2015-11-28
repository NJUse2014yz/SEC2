package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryPO;

/**
 * 
 * @author zhangqi
 *
 */
public class InventoryDataDriver {
	public void drive(InventoryDataService ids) throws RemoteException{
		ResultMessage result=ids.insert(null);
		
			System.out.println("Insert!");
		
		
		result=ids.update(null);
		
			System.out.println("update!");
		
		
		result=ids.init();
		
			System.out.println("init!");
		

		
		
		
		ArrayList<InventoryPO> array3=ids.findAll();
		System.out.println(array3.size());
		
		
	}
	
//	public static void main(String[] args) throws RemoteException {
//		InventoryDataService ids=new InventoryDataStub();
//		InventoryDataDriver idd=new InventoryDataDriver ();
//		idd.drive(ids);
//	}
}
