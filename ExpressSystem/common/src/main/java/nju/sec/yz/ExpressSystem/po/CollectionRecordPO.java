package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;

public class CollectionRecordPO implements Serializable{

	private String time;
	private double amount;
	private String deliverId;//快递员id
	private String positionId;//营业厅id
	private String barId;
	
	public CollectionRecordPO(String barId,String time, double amount, String deliverId, String positionId) {
		super();
		this.time = time;
		this.amount = amount;
		this.deliverId = deliverId;
		this.positionId = positionId;
		this.barId=barId;
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
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getBarId() {
		return barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}
	
	
}
