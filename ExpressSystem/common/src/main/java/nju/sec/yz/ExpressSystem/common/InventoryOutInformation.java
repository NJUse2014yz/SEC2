package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class InventoryOutInformation  implements Serializable{
	private String time;
	private String destination;
	private TransportType transportType;
	private String transitSheetId;//中转单编号
	private String transitId;//中转中心编号
	
	public InventoryOutInformation(String time, String destination,
			TransportType transportType, String transitSheetId) {
		super();
		this.time = time;
		this.destination = destination;
		this.transportType = transportType;
		this.setTransitSheetId(transitSheetId);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	public String getTransitId() {
		return transitId;
	}
	public void setTransitId(String transitId) {
		this.transitId = transitId;
	}
	public String getTransitSheetId() {
		return transitSheetId;
	}
	public void setTransitSheetId(String transitSheetId) {
		this.transitSheetId = transitSheetId;
	}
}
