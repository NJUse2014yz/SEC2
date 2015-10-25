package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *
 */
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.TransitInformation;

public class TransitSheetVO extends ReceiptVO{
	private TransitInformation transitInformation;
	private ArrayList<String> barIds;
	private double fare;
	
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
}
