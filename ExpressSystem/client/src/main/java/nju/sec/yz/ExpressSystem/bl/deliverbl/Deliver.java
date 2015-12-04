package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;

/**
 * 物流信息的领域模型对象
 * 负责更新物流信息和查看物流信息
 * @author 周聪
 *
 */
public class Deliver {
	private DeliverDataService data;
	
	public Deliver(){
		try {
			data=DatafactoryProxy.getDeliverDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看物流信息
	 */
	public DeliverVO checkDeliver(String barId) {
		DeliverVO vo=new DeliverVO();
		try {
			DeliverPO po=data.find(barId);
			if(po==null)
				return null;
			vo.barId=po.getBarId();
			vo.state=po.getState();
			vo.trails.addAll(po.getTrails());
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return vo;
	}
	
	/**
	 * 寄件单通过审批后通过此方法更新寄件信息
	 * 此时新建该条形码号对应的物流信息
	 */
	public ResultMessage newDeliverInfo(String barId,String trail){
		//新建初始物流信息
		ResultMessage message=null;
		DeliverPO deliverPO=new DeliverPO(barId);
		deliverPO.addTrail(trail);
		
		//将物流信息持久化
		try {
			message=data.insert(deliverPO);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * 其他单据通过此方法更新信息
	 */
	public ResultMessage updateDeliverInfo(String barId,String trail,DeliveryState state) {
		
		//查找物流信息
		DeliverVO info=this.checkDeliver(barId);
		if(info==null)
			return new ResultMessage(Result.FAIL,"物流信息更新失败");
		
		//修改物流消息
		info.barId=barId;
		info.trails.add(trail);
		info.state=state;
		
		//将修改持久化
		DeliverPO po=new DeliverPO(info.state, info.barId, info.trails);
		ResultMessage message=this.updateDeliverData(po);
		
		return message;
	}
	
	
	/**
	 * 保存更新到数据层
	 */
	private ResultMessage updateDeliverData(DeliverPO deliverPO){
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		try {
			message=data.update(deliverPO);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return message;
	}
	
}
