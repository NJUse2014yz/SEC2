package nju.sec.yz.ExpressSystem.common;
/**
 * 
 * @author YU Fan
 *
 */
public class PaymentInformation {
	private String time;
	private double amount;
	private String inDeliverId;
	
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
	
}