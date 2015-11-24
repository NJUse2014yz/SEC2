package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

public class DeliverBlStub implements DeliverBlService{
	//物流历史轨迹
	public OrderVO  checkDeliver(String id){
		OrderVO order=new OrderVO();
		order.setOrderInformation(new OrderInformation());
		
		return order;
	}
	//寄件单
	public ResultMessage deliverReceipt (SendSheetVO vo){
		return null;
	}
	//收件单
	public ResultMessage  recieveReceipt(ReceiveVO vo){
		return null;
	}
	//营业厅装车单
	public ResultMessage positionLoadingReceipt (OfficeLoadSheetVO vo){
		return null;
	}
	//营业厅到达单
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo){
		return null;
	}
	//营业厅派件单
	public ResultMessage positionSendReceipt(DeliverySheetVO vo){
		return null;
	}
	//中转中心到达单
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo){
		return null;
	}
	//中转中心装车单
	public ResultMessage transitLoadingReceipt (TransitLoadSheetVO vo){
		return null;
	}
	@Override
	public ResultMessage transitReceipt(TransitSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SendSheetVO checkDeliverReceipt(String barID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CollectionRecordVO> getCollectionRecords() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
