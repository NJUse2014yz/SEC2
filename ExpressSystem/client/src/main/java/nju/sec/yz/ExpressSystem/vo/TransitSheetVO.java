package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *中转单
 */
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.TransitInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;

public class TransitSheetVO extends ReceiptVO{
	private TransitInformation transitInformation;
	private ArrayList<String> barIds;
	private double fare;
	private TransportType type;//运输方式，汽车火车飞机
	
	public TransitInformation getTransitInformation() {
		return transitInformation;
	}
	public void setTransitInformation(TransitInformation transitInformation) {
		this.transitInformation = transitInformation;
	}
	public ArrayList<String> getBarIds() {
		return barIds;
	}
	public void setBarIds(ArrayList<String> barIds) {
		this.barIds = barIds;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public TransportType getTransportType() {
		return type;
	}
	public void setTransportType(TransportType type) {
		this.type = type;
	}
}
