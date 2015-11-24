package nju.sec.yz.ExpressSystem.vo;
/**
 * 收款记录
 * @author 周聪
 *
 */
public class CollectionRecordVO {

	private String time;
	private double amount;
	private String deliverId;//快递员id
	private String barId;
	
	public CollectionRecordVO(String barId,String time, double amount, String deliverId) {
		super();
		this.time = time;
		this.amount = amount;
		this.deliverId = deliverId;
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
	public String getBarId() {
		return barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}
	
	
}
