package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.OrderVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

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
	 * TODO  暂定
	 * 前置条件	物流单据通过审批
	 * 后置条件       将vo中的信息解析为物流信息并更新到DeliverDataBase中
	 */
	public ResultMessage updateDeliver(ReceiptVO vo){
		return null;
	}
}
