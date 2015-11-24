package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class ArriveInformation implements Serializable{
	public ArriveInformation(String departure,String time,  ArriveState state, String transitId) {
		super();
		this.time = time;
		this.departure = departure;
		this.state = state;
		this.transitId = transitId;
	}
	private String time;
	private String transitSheetId;
	private String departure;
	private ArriveState state;
	private String transitId;
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTransitSheetId() {
		return transitSheetId;
	}
	public void setTransitSheetId(String transitSheetId) {
		this.transitSheetId = transitSheetId;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public ArriveState getState() {
		return state;
	}
	public void setState(ArriveState state) {
		this.state = state;
	}
	
	
} 
