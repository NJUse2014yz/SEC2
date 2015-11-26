package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author YU Fan
 *
 */
public class TransitInformation  implements Serializable{
	protected String time;//日期
	protected String departure;//出发地
	protected String destination;//到达单
	protected String transiterId;//押运员
	protected ArrayList<String> barIds;//
	protected double fare;//运费
	
	public TransitInformation() {
		
	}
	
	protected TransitInformation(String time, String departure, String destination, String transiterId,
			ArrayList<String> barIds) {
		super();
		this.time = time;
		this.departure = departure;
		this.destination = destination;
		this.transiterId = transiterId;
		this.barIds = barIds;
	}
	
	
	public TransitInformation(TransitInformation info){
		this.time=info.getTime();
		this.departure=info.getDeparture();
		this.barIds=info.getBarIds();
		this.destination=info.getDestination();
		this.transiterId=info.getTransiterId();
	}
	
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
