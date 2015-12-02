package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.BarIdsDataService;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;

/**
 * 管理中转单的条形码号
 * @author 周聪
 *
 */
public class BarIdList {

	private BarIdsDataService data;
	
	public BarIdList() {
		try {
			data=DatafactoryProxy.getBarIdsDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addBarIds(BarIdsPO po){
		try {
			data.add(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入中转单编号获得条形码号列表
	 */
	public List<String> getBarIds(String transitSheetId){
		BarIdsPO po=null;
		try {
			po=data.get(transitSheetId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(po==null)
			return null;
		
		return po.getBarIds();
	}
	
	//TODO
	public void deleteBarIds(String transitSheetId){
		
	}
	
	
	
}
