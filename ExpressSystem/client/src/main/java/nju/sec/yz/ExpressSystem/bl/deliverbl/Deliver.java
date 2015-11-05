package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

/**
 * 物流信息的领域模型对象
 * 负责更新物流信息和查看物流信息
 * @author 周聪
 *
 */
public class Deliver {
	/**
	 * 查看物流信息
	 */
	public OrderVO checkDeliver(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 寄件单通过审批后通过此方法更新寄件单信息
	 */
	public ResultMessage updateDeliverReceipt(SendSheetPO po){
		return null;
	}
	
}
