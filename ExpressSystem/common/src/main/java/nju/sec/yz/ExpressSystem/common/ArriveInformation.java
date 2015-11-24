package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
public class ArriveInformation implements Serializable{

	private String time;//到达日期
	private String transitSheetId;//中转单编号
	private String departure;//出发地
	private ArrayList<ArriveState> state;//货物到达状态（损坏、完整、丢失）
	
	
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
	public ArrayList<ArriveState> getState() {
		return state;
	}
	public void setState(ArrayList<ArriveState> state) {
		this.state = state;
	}
	
	
} 
