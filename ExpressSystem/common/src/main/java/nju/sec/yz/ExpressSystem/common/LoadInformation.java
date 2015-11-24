package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 营业厅装车单 、中转中心装车单
 * @author YU Fan
 */
public class LoadInformation  implements Serializable{
	public LoadInformation(String time, String agencyId, String destinationId, String carId, String officerId,
			String driverId) {
		super();
		this.time = time;
		this.agencyId = agencyId;
		this.destinationId = destinationId;
		this.carId = carId;
		this.officerId = officerId;
		this.driverId = driverId;
	}
	private String time;//装车日期
	private String agencyId;//营业厅编号 （025（城市编码）+000三位数字）
	private String transportId;//汽运编号 （营业厅编号+日期+00000五位数字）
	private String destinationId;//到达地（本地中转中心或者其它营业厅）
	private String carId;//车辆代号
	private String officerId;//
	private String driverId;//
	private double fare;//运费
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	public String getDestination() {
		return destinationId;
	}
	public void setDestination(String destinationId) {
		this.destinationId = destinationId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getOfficerId() {
		return officerId;
	}
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
}
