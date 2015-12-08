package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class CityInformation  implements Serializable{
	private String fromCity;
	private String fromID;//城市编号
	private String toCity;
	private String toID;
	private double distance;
	
	public CityInformation(String from,String fromID,String to,String toID,double distance) {
		this.fromCity=from;
		this.toCity=to;
		this.distance=distance;
		this.fromID=fromID;
		this.toID=toID;
	}
	
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}
	
}
