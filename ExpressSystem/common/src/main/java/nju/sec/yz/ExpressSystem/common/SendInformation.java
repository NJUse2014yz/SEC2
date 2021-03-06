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
	private double costForAll=0.0;
	//
	private int predictTime=0;
	
	public SendInformation(String barId, ToAndFromInformation toPerson, ToAndFromInformation fromPerson,
			GoodInformation good, DeliveryType deliveryType, PackType packType) {
		super();
		this.barId = barId;
		this.toPerson = toPerson;
		this.fromPerson = fromPerson;
		this.good = good;
		this.deliveryType = deliveryType;
		this.packType = packType;
		switch(packType){
		case BAG:costForPack=1.0;break;
		case PAPER:costForPack=5.0;break;
		case WOOD:costForPack=10.0;break;
		default:
			break;
		}
	}
	public SendInformation() {
		// TODO Auto-generated constructor stub
	}
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
	public int getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(int predictTime) {
		this.predictTime = predictTime;
	}

}
