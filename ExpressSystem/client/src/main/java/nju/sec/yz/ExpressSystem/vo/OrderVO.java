package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *订单信息
 */
import nju.sec.yz.ExpressSystem.common.OrderInformation;
/**
 * 用于传递deliverＰＯ的值
 * @author 周聪
 *　TODO 
 */
public class OrderVO {
	private OrderInformation orderInformation=null;

	public OrderInformation getOrderInformation() {
		return orderInformation;
	}

	public void setOrderInformation(OrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}
}
