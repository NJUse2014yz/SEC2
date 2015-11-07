package nju.sec.yz.ExpressSystem.common;

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitInformation {
	protected String time;
	protected String departure;
	protected String destination;
	protected String transiterId;
	protected ArrayList<String> barIds;
	protected double fare;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTransiterId() {
		return transiterId;
	}
	public void setTransiterId(String transiterId) {
		this.transiterId = transiterId;
	}
	public ArrayList<String> getBarIds() {
		return barIds;
	}
	public void setBarIds(ArrayList<String> barIds) {
		this.barIds = barIds;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
}
