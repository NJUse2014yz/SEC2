package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
/**
 * 负责物流信息模块的逻辑控制
 * @author 周聪
 *
 */
public class DeliverController implements DeliverBlService{

	@Override
	public OrderVO checkDeliver(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deliverReceipt(SendSheetVO vo) {
		DeliverReceipt receipt=new DeliverReceipt();
		receipt.makeDeliverReceipt(vo);
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage recieveReceipt(ReceiveVO vo) {
		// TODO Auto-generated method stub
		return null;
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

}
