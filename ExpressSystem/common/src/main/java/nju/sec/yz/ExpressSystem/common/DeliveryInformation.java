package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 派件单
 * @author YU Fan
 *
 */
public class DeliveryInformation  implements Serializable{
	private String time;
	private String outDeliverId;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDeliverId() {
		return outDeliverId;
	}
	public void setDeliverId(String deliverId) {
		this.outDeliverId = deliverId;
	}
}
