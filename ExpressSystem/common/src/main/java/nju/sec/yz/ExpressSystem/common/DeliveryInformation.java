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
	private String barId;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getOutDeliverId() {
		return outDeliverId;
	}
	public void setOutDeliverId(String outDeliverId) {
		this.outDeliverId = outDeliverId;
	}
	public String getBarId() {
		return barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}
	
}
