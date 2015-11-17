package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public class CityInformation  implements Serializable{
	private String fromCity;
	private String toCity;
	private double distance;
	
	public CityInformation(String from,String to,double distance) {
		this.fromCity=from;
		this.toCity=to;
		this.distance=distance;
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
	
}
