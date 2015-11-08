package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitFlightInformation extends TransitInformation implements Serializable{
	private String flightTransitId;
	private String flightId;
	private String shelfId;
	public String getFlightTransitId() {
		return flightTransitId;
	}
	public void setFlightTransitId(String flightTransitId) {
		this.flightTransitId = flightTransitId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	
}
