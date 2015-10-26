package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.stub.InventoryDataStub;
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
		if(result==ResultMessage.SUCCESS){
			System.out.println("Insert!");
		}
		
		result=ids.update(null);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update!");
		}
		
		result=ids.init();
		if(result==ResultMessage.SUCCESS){
			System.out.println("init!");
		}
		
		ArrayList<InventoryPO> array1=ids.findByTime(null, null);
		System.out.println(array1.size());
		
		ArrayList<InventoryPO> array2=ids.findByPosition(null);
		System.out.println(array2.size());
		
		ArrayList<InventoryPO> array3=ids.findAll();
		System.out.println(array3.size());
		
		
	}
	
	public static void main(String[] args) throws RemoteException {
		InventoryDataService ids=new InventoryDataStub();
		InventoryDataDriver idd=new InventoryDataDriver ();
		idd.drive(ids);
	}
}
