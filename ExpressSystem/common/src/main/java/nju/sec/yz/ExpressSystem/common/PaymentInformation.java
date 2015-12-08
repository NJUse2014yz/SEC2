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
	private String account;//收款账户名
	
	public PaymentInformation(String time, double amount, String inDeliverId,
			String positionId,String account) {
		super();
		this.time = time;
		this.amount = amount;
		this.inDeliverId = inDeliverId;
		this.positionId = positionId;
		this.account=account;
	}
	
	public PaymentInformation() {
		// TODO Auto-generated constructor stub
	}
	
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
