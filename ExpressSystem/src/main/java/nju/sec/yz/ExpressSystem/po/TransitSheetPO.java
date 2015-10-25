package nju.sec.yz.ExpressSystem.po;
import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.TransitInformation;

public class TransitSheetPO extends ReceiptPO{
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
