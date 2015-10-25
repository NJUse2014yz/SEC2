package nju.sec.yz.ExpressSystem.common;

public class InventoryOutInformation {
	private String time;
	private String destination;
	private TransportType transportType;
	private String transitId;
	
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
