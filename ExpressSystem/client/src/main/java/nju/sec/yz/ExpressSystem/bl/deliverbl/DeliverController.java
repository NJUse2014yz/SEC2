package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;
/**
 * 负责物流信息模块的逻辑控制
 * @author 周聪
 *
 */
public class DeliverController implements DeliverBlService{

	
    @Override
	/**
	 * 快递员查询订单信息
	 */
	public SendSheetVO checkDeliverReceipt(String barID) {
		DeliverReceipt receipt=new DeliverReceipt();
		return receipt.getOrder(barID);
	}
	
	@Override
	public DeliverVO checkDeliver(String id) {
		Deliver deliver=new Deliver();
		return deliver.checkDeliver(id);
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
		PositionLoadingReceipt receipt=new PositionLoadingReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo) {
		PositionReceiveReceipt receipt=new PositionReceiveReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage positionSendReceipt(DeliverySheetVO vo) {
		PositionSendReceipt send=new PositionSendReceipt();
		ResultMessage message=send.make(vo);
		return message;
	}

	@Override
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo) {
		TransitReceiveReceipt receipt=new TransitReceiveReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage transitLoadingReceipt(TransitLoadSheetVO vo) {
		TransitLoadingReceipt receipt=new TransitLoadingReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}


	

	@Override
	/**
	 * 获得快递员收款记录
	 */
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

	@Override
	public ResultMessage transitFlightReceipt(TransitSheetVO vo) {
		TransitFlightReceipt receipt=new TransitFlightReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage transitTrainReceipt(TransitSheetVO vo) {
		TransitTrainReceipt receipt=new TransitTrainReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public ResultMessage transitCarReceipt(TransitSheetVO vo) {
		TransitCarReceipt receipt=new TransitCarReceipt();
		ResultMessage message=receipt.make(vo);
		return message;
	}

	@Override
	public TransitVO getCurrentTransit() {
		TransitLoadingReceipt receipt=new TransitLoadingReceipt();
		return receipt.getCurrentTransit();
	}

	@Override
	public PositionVO getCurrentPosition() {
		PositionLoadingReceipt receipt=new PositionLoadingReceipt();
		return receipt.getCurrentPosition();
	}

	@Override
	public List<String> getValidAgency() {
		PositionLoadingReceipt receipt=new PositionLoadingReceipt();
		return receipt.getValidAgency();
	}

}
