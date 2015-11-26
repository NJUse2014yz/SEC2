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
	private String transitId;
	
	public InventoryOutInformation(String time, String destination,
			TransportType transportType, String transitId) {
		super();
		this.time = time;
		this.destination = destination;
		this.transportType = transportType;
		this.transitId = transitId;
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
}
