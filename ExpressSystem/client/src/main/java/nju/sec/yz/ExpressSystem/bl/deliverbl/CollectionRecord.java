package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.CollectionRecordDataService;
import nju.sec.yz.ExpressSystem.po.CollectionRecordPO;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;

/**
 * 管理收款记录
 * @author 周聪
 *
 */
public class CollectionRecord {

	private CollectionRecordDataService data;
	
	public CollectionRecord() {
		try {
			data=DatafactoryProxy.getCollectionRecordDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	public void addRecord(CollectionRecordPO po){
		try {
			data.addRecord(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	
	public void deleteRecord(String barId){
		try {
			data.deleteRecord(barId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	public List<CollectionRecordVO> getRecords(){
		
		String positionId=this.getCurrentPositionID();
		System.out.println(positionId);
		List<CollectionRecordVO> vos=new ArrayList<>();
		List<CollectionRecordPO> pos=null;
		
		try {
			pos=data.getRecords(positionId);
			System.out.println(pos.size());
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		for(CollectionRecordPO po:pos){
			CollectionRecordVO vo=this.changePOToVO(po);
			vos.add(vo);
		}
		
		return vos;
	}
	
	/**
	 * 从user获得当前的营业厅id
	 * 营业厅业务员编号规则：营业厅编号+C+000三位数字
	 */
	private String getCurrentPositionID(){
		User user=new User();
		String positionerID=user.getCurrentID();
		String positionID=positionerID.split("C")[0];
		return positionID;
	}
	
	private CollectionRecordVO changePOToVO(CollectionRecordPO po){
		CollectionRecordVO vo=new CollectionRecordVO(po.getBarId(), po.getTime(),
											po.getAmount(), po.getDeliverId());
		return vo;
	}
	
}
