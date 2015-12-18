package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptCountPO;

/**
 * 记录相关人员填写的单据的数量
 * 根据数量生成id
 * @author 周聪
 *
 */
public class ReceiptID {

	private ReceiptCounterDataService counterData;
	
	public ReceiptID() {
		try {
			counterData=DatafactoryProxy.getCounterDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	private void add(ReceiptCountPO po){
		try {
			counterData.add(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	private ReceiptCountPO get(String id,IdType type){
		ReceiptCountPO po=null;
		try {
			po=counterData.get(id, type);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return po;
	}
	
	private void update(ReceiptCountPO po){
		try {
			counterData.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成表单id
	 * @param id    
	 * @param type 表单类型
	 * @return
	 */
	public String getID(String id,IdType type){
		String receiptID=id;
		String date=TimeTool.getDate();
		receiptID=receiptID+type.getIdStr()+date;
		
		ReceiptCountPO po=this.get(id, type);
		String count="";
		//找不到或日期不是今天 
		if(po==null){
			this.add(new ReceiptCountPO(id, date, type));
			count="0";
		}else if(!po.getDate().equals(date)){
			this.update(new ReceiptCountPO(id, date, type));
			count="0";
		}else{
			count = po.getCount() + "";
			po.addCount();
			this.update(po);
		}
		
		while (count.length() != type.getLength()) {
			count = "0" + count;
		}
		
		receiptID=receiptID+count;
		
		return receiptID;
	}
	
	
}
