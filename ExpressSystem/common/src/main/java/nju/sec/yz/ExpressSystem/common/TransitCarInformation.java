package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitCarInformation extends TransitInformation implements Serializable{
	
	private String carTransitId;
	private String carId;
	private String driverId;
	
	public TransitCarInformation(String time, String departure, String destination, String transiterId,
			ArrayList<String> barIds) {
		super(time, departure, destination, transiterId, barIds);
	}
	
	
	
	public TransitCarInformation(TransitCarInformation info) {
		super(info);
		this.carTransitId = info.getCarTransitId();
		this.carId = info.getCarId();
		this.driverId = info.getDriverId();
	}



	public String getCarTransitId() {
		return carTransitId;
	}
	public void setCarTransitId(String carTransitId) {
		this.carTransitId = carTransitId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
}
