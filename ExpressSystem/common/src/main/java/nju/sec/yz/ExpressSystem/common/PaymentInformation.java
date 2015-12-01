package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class PaymentInformation  implements Serializable{
	private String time;
	private double amount;
	private String inDeliverId;
	private String positionId;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getInDeliverId() {
		return inDeliverId;
	}
	public void setInDeliverId(String inDeliverId) {
		this.inDeliverId = inDeliverId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
}
