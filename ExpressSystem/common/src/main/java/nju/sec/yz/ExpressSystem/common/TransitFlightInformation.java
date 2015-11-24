package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitFlightInformation extends TransitInformation implements Serializable{
	
	private String flightTransitId;
	private String flightId;
	private String shelfId;
	
	
	public TransitFlightInformation(String time, String departure, String destination, String transiterId,
			ArrayList<String> barIds) {
		super(time, departure, destination, transiterId, barIds);
	}
	
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
