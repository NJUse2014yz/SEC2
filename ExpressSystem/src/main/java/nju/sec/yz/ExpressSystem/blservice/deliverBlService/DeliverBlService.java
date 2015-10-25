package nju.sec.yz.ExpressSystem.blservice.deliverBlService;

/**
 * @author xiaosaisai
 * 物流接口
 */
public class DeliverBlService {
	//物流历史轨迹
	public DeliverVO  checkDeliver(String id);
	//寄件单
	public ResultMessage deliverReceipt (deliverVO vo);
	//收件单
	public ResultMessage  recieveReceipt(recieveDeliverVO vo);
	//营业厅装车单
	public ResultMessage positionLoadingReceipt (positionLoadingVO vo);
	//营业厅到达单
	public ResultMessage positionReceiveReceipt(positionReceiveVO vo);
	//营业厅派件单
	public ResultMessage positionSendReceipt(positionSendVO vo);
	//中转中心到达单
	public ResultMessage transitReceiveReceipt(trancitReceiveVO vo);
	//中转中心装车单
	public ResultMessage transitLoadingReceipt (transitLoadingVO vo);
}

