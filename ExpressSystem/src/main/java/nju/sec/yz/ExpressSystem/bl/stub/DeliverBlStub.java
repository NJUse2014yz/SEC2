package nju.sec.yz.ExpressSystem.bl.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;

public class DeliverBlStub implements DeliverBlService{
	//物流历史轨迹
	public OrderVO  checkDeliver(String id){
		return new OrderVO();
	}
	//寄件单
	public ResultMessage deliverReceipt (SendSheetVO vo){
		return ResultMessage.SUCCESS;
	}
	//收件单
	public ResultMessage  recieveReceipt(ReceiveVO vo){
		return ResultMessage.SUCCESS;
	}
	//营业厅装车单
	public ResultMessage positionLoadingReceipt (OfficeLoadSheetVO vo){
		return ResultMessage.SUCCESS;
	}
	//营业厅到达单
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo){
		return ResultMessage.SUCCESS;
	}
	//营业厅派件单
	public ResultMessage positionSendReceipt(DeliverySheetVO vo){
		return ResultMessage.SUCCESS;
	}
	//中转中心到达单
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo){
		return ResultMessage.SUCCESS;
	}
	//中转中心装车单
	public ResultMessage transitLoadingReceipt (TransitLoadSheetVO vo){
		return ResultMessage.SUCCESS;
	}
}
