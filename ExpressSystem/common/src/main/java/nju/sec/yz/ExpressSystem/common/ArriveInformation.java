package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class ArriveInformation implements Serializable{
	private String time;
	private String transitSheetId;
	private String departure;
	private ArriveState state;
	
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
