package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.BarIdsDataService;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.vo.BarIdsVO;

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
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	public void addBarIds(BarIdsPO po){
		try {
			data.add(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入中转单编号获得条形码号列表
	 */
	public BarIdsVO getBarIds(String transitSheetId){
		BarIdsPO po=null;
		try {
			po=data.get(transitSheetId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		if(po==null)
			return null;
		
		
		BarIdsVO vo=new BarIdsVO();
		vo.barIds.addAll(po.getBarIds());
		vo.fromAgency=po.getFromAgency();
		
		return vo;
	}
	
	/**
	 * 输入中转单编号获得条形码号列表
	 * 需验证机构id
	 */
	public BarIdsVO getBarIds(String transitSheetId,String destinationId){
		BarIdsPO po=null;
		try {
			po=data.get(transitSheetId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		if(po==null)
			return null;
		
		//到达地不是当前机构
		if(!po.getDestinationId().equals(destinationId))
			return null;
		
		BarIdsVO vo=new BarIdsVO();
		vo.barIds.addAll(po.getBarIds());
		vo.fromAgency=po.getFromAgency();
		
		return vo;
		
	}
	
	public void arrive(String transitSheetId){
		
		try {
		BarIdsPO po=data.get(transitSheetId);
		if(po==null)
			return;
		po.arrive();
		data.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 中转单对应的到达单是否填过？
	 */
	public boolean isArrived(String transitSheetId){
		BarIdsPO po=null;
		try {
			po=data.get(transitSheetId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		if(po==null)
			return false;
		return po.isArrived();
	}
	
	//TODO
	public void deleteBarIds(String transitSheetId){
		
	}
	
	
	
}
