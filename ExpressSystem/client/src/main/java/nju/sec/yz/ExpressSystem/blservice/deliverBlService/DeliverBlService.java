package nju.sec.yz.ExpressSystem.blservice.deliverBlService;

import java.util.List;

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
 * @author xiaosaisai
 * @change YU Fan 
 * 物流接口
 */
public interface DeliverBlService {
	//物流历史轨迹
	public OrderVO  checkDeliver(String id);
	//寄件单
	public ResultMessage deliverReceipt (SendSheetVO vo);
	/**
	 * @author cong
	 * 快递员查询订单(寄件单)信息
	 */
	public SendSheetVO checkDeliverReceipt(String barID);
	
	//收件单
	public ResultMessage  recieveReceipt(ReceiveVO vo);
	//营业厅装车单
	public ResultMessage positionLoadingReceipt (OfficeLoadSheetVO vo);
	//营业厅到达单
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo);
	//营业厅派件单
	public ResultMessage positionSendReceipt(DeliverySheetVO vo);
	//中转中心到达单
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo);
	//中转中心装车单
	public ResultMessage transitLoadingReceipt (TransitLoadSheetVO vo);
	//中转单
	public ResultMessage transitReceipt (TransitSheetVO vo);
	//获得营业该厅的收款记录
	public List<CollectionRecordVO> getCollectionRecords();
}

