package nju.sec.yz.ExpressSystem.bl.driver;

import nju.sec.yz.ExpressSystem.bl.stub.DeliverBlStub;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

/**
 * 
 * @author 周聪
 *
 * 物流逻辑测试驱动
 */
public class DeliverBlDriver {

	public void drive(DeliverBlService deliverBl_stub){
		//
		OrderVO order=deliverBl_stub.checkDeliver(null);
		System.out.println("物流状态 ： "+order.getOrderInformation().getDeliveryState());
		
		//
		ResultMessage deliverResult=deliverBl_stub.deliverReceipt(null);
		if(deliverResult==ResultMessage.SUCCESS)
			System.out.println("寄件单填写成功");
		else
			System.out.println("寄件单填写失败");
		
		//
		ResultMessage positionLoadingResult=deliverBl_stub.positionLoadingReceipt(null);
		if(positionLoadingResult==ResultMessage.SUCCESS)
			System.out.println("营业厅装车单填写成功");
		else
			System.out.println("营业厅装车单填写失败");
		
		//
		ResultMessage positionReceiveResult=deliverBl_stub.positionReceiveReceipt(null);
		if(positionReceiveResult==ResultMessage.SUCCESS)
			System.out.println("营业厅到达单填写成功");
		else
			System.out.println("营业厅到达单填写失败");
		//
		ResultMessage positionSendResult=deliverBl_stub.positionSendReceipt(null);
		if(positionSendResult==ResultMessage.SUCCESS)
			System.out.println("营业厅派件单填写成功");
		else
			System.out.println("营业厅派件单填写失败");
		//
		ResultMessage recieveResult=deliverBl_stub.recieveReceipt(null);
		if(recieveResult==ResultMessage.SUCCESS)
			System.out.println("接收单填写成功");
		else
			System.out.println("接收单填写失败");
		//
		ResultMessage transitLoadingResult=deliverBl_stub.transitLoadingReceipt(null);
		if(transitLoadingResult==ResultMessage.SUCCESS)
			System.out.println("中转中心装车单填写成功");
		else
			System.out.println("中转中心装车单填写失败");
		//
		ResultMessage transitReceiveResult=deliverBl_stub.transitReceiveReceipt(null);
		if(transitReceiveResult==ResultMessage.SUCCESS)
			System.out.println("中转中心装车单填写成功");
		else
			System.out.println("中转中心装车单填写失败");
	}
	
//	public static void main(String[] args) {
//		DeliverBlService deliverBl_stub=new DeliverBlStub();
//		new DeliverBlDriver().drive(deliverBl_stub);
//	}
	
	
}
