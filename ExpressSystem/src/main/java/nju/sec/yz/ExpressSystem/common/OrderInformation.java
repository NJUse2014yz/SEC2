package nju.sec.yz.ExpressSystem.common;

import java.util.ArrayList;

public class OrderInformation {
	private String barId;
	private ToAndFromInformation toPerson;
	private ToAndFromInformation fromPerson;
	private GoodInformation good;
	private DeliveryType deliveryType;
	private PackType packType;
	private double costForPack;//?
	private double costForAll;
	private String predictTime;
	private DeliveryState deliveryState;
	private ArrayList<String> trail;
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
	public DeliveryState getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(DeliveryState deliveryState) {
		this.deliveryState = deliveryState;
	}
	public ArrayList<String> getTrail() {
		return trail;
	}
	public void setTrail(ArrayList<String> trail) {
		this.trail = trail;
	}
	
	
}
