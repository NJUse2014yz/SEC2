package nju.sec.yz.ExpressSystem.vo;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.DeliveryState;

public class DeliverVO {

	public DeliveryState state;

	public String barId;// 标识快递的十位数字条形码号

	public List<String> trails;// 轨迹+" "+时间
}
