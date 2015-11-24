package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
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
/**
 * 负责物流信息模块的逻辑控制
 * @author 周聪
 *
 */
public class DeliverController implements DeliverBlService{

	
//	@Override
	/**
	 * 快递员查询订单信息
	 */
	public SendSheetVO checkDeliverReceipt(String barID) {
		DeliverReceipt receipt=new DeliverReceipt();
		return receipt.getOrder(barID);
	}
	
	@Override
	public OrderVO checkDeliver(String id) {
		
		return null;
	}

	@Override
	public ResultMessage deliverReceipt(SendSheetVO vo) {
		DeliverReceipt receipt=new DeliverReceipt();
		ResultMessage resultMessage=receipt.make(vo);
		return resultMessage;
	}

	@Override
	public ResultMessage recieveReceipt(ReceiveVO vo) {
		RecieveReceipt receipt=new RecieveReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage positionLoadingReceipt(OfficeLoadSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage positionSendReceipt(DeliverySheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage transitLoadingReceipt(TransitLoadSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResultMessage transitReceipt(TransitSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CollectionRecordVO> getCollectionRecords() {
		CollectionRecord record=new CollectionRecord();
		List<CollectionRecordVO> records=record.getRecords();
		return records;
	}

	@Override
	/**
	 * 输入中转单编号获得条形码号列表
	 */
	public List<String> getBarIdList(String transitSheetId) {
		BarIdList list=new BarIdList();
		List<String> barIds=list.getBarIds(transitSheetId);
		return barIds;
	}

}
