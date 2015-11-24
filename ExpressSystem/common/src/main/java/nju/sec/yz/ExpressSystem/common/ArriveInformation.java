package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class ArriveInformation implements Serializable{

	private String time;//到达日期
	private String transitSheetId;//中转单编号
	private String departure;//出发地
	private ArriveState state;//货物到达状态（损坏、完整、丢失）
	
	private String transitId;

	public String getTransitId() {
		return transitId;
	}

	public void setTransitId(String transitId) {
		this.transitId = transitId;
	}

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
