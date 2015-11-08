package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitCarInformation extends TransitInformation implements Serializable{
	private String carTransitId;
	private String carId;
	private String driverId;
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
