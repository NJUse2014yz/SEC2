package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;

public class DeliverMockObject {
	/**
	 * 寄件单通过审批后通过此方法更新寄件单信息
	 */
	public ResultMessage updateDeliverReceipt(SendSheetPO po){
		System.out.println("updating a deliver message...");
		
		OrderInformation ordermation=new OrderInformation();
		ordermation.setSendInformation(po.getSendInformation());
		
		return null;
	}
}
