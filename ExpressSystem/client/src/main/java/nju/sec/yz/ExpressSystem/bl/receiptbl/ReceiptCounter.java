package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptCountPO;

/**
 * 记录相关人员填写的单据的数量
 * @author 周聪
 *
 */
public class ReceiptCounter {

	private ReceiptCounterDataService counterData;
	
	public ReceiptCounter() {
		try {
			counterData=DatafactoryProxy.getCounterDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(ReceiptCountPO po){
		try {
			counterData.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ReceiptCountPO get(String id,ReceiptType type){
		ReceiptCountPO po=null;
		try {
			po=counterData.get(id, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return po;
	}
	
	public void update(ReceiptCountPO po){
		try {
			counterData.update(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
