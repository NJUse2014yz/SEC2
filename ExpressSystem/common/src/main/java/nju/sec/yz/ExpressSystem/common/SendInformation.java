package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class SendInformation  implements Serializable{
	//订单条形码号
	private String barId;
	//收件人姓名、住址、单位、电话、手机
	private ToAndFromInformation toPerson;
	//寄件人姓名、住址、单位、电话、手机
	private ToAndFromInformation fromPerson;
	//托运货物信息（原件数、实际重量、体积、内件品名）
	private GoodInformation good;
	//经济快递，标准快递，特快专递
	private DeliveryType deliveryType;
	//
	private PackType packType;
	//包装费（纸箱（5元）、木箱（10元）、快递袋（1元）、其它）
	private double costForPack;//?
	//费用合计（自动计算，运费+包装费）
	private double costForAll;
	//
	private String predictTime;
	
	public String getBarId() {
		return barId;
	}
	public void setBarId(String barId) {
		this.barId = barId;
	}
	public ToAndFromInformation getToPerson() {
		return toPerson;
	}
	public void setToPerson(ToAndFromInformation toPerson) {
		this.toPerson = toPerson;
	}
	public ToAndFromInformation getFromPerson() {
		return fromPerson;
	}
	public void setFromPerson(ToAndFromInformation fromPerson) {
		this.fromPerson = fromPerson;
	}
	public GoodInformation getGood() {
		return good;
	}
	public void setGood(GoodInformation good) {
		this.good = good;
	}
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}
	public PackType getPackType() {
		return packType;
	}
	public void setPackType(PackType packType) {
		this.packType = packType;
	}
	public double getCostForPack() {
		return costForPack;
	}
	public void setCostForPack(double costForPack) {
		this.costForPack = costForPack;
	}
	public double getCostForAll() {
		return costForAll;
	}
	public void setCostForAll(double costForAll) {
		this.costForAll = costForAll;
	}
	public String getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

}
