package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.rmi.RemoteException;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

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
			//TODO 远程异常
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看物流信息
	 */
	public OrderVO checkDeliver(String id) {
		try {
			DeliverPO po=data.find(id);
		} catch (RemoteException e) {
			//TODO 远程异常
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 寄件单通过审批后通过此方法更新寄件单信息
	 */
	public ResultMessage updateDeliverReceipt(SendSheetPO po){
		SendInformation imfo=po.getSendInformation();
		ResultMessage message=null;
		DeliverPO deliverPO=new DeliverPO(imfo.getBarId());
		try {
			message=data.insert(deliverPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
}
