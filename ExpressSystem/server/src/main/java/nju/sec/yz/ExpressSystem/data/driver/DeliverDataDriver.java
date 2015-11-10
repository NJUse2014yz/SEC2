package nju.sec.yz.ExpressSystem.data.driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.data.deliverdata.DeliverDataImpl;
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
		//插入的po
		DeliverPO po=new DeliverPO();
		SendInformation sendInformation=new SendInformation();
		sendInformation.setBarId("1");
		OrderInformation orderInformation=new OrderInformation();
		orderInformation.setSendInformation(sendInformation);
		po.setOrdermation(orderInformation);
		
		//更新的po
		DeliverPO updatePO=new DeliverPO();
		SendInformation updateSendInformation=new SendInformation();
		updateSendInformation.setBarId("1");
		OrderInformation updateOrderInformation=new OrderInformation();
		updateOrderInformation.setSendInformation(updateSendInformation);
		updatePO.setOrdermation(updateOrderInformation);
		
		ResultMessage result=dds.insert(po);
		if(result==ResultMessage.SUCCESS){
			System.out.println("insert");
		}
		
		result=dds.update(updatePO);
		if(result==ResultMessage.SUCCESS){
			System.out.println("update");
		}
		
		DeliverPO getPO=dds.find("1");
		if(getPO!=null)
			System.out.println("get a PO");
		
		ArrayList<DeliverPO> array=dds.findAll();
		System.out.println(array.size());
				
	}
	
	public static void main(String[] args) throws RemoteException {
		DeliverDataService dds=new DeliverDataImpl();
		DeliverDataDriver ddd=new DeliverDataDriver();
		ddd.drive(dds);
		
	}
}
