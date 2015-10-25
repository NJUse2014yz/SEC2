package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *营业厅装车单
 */

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.LoadInformation;

public class OfficeLoadSheetVO {
	private LoadInformation officeLoadInformation;
	private ArrayList<String> barIds;
	private double fare;
	
	public LoadInformation getOfficeLoadInformation() {
		return officeLoadInformation;
	}
	public void setOfficeLoadInformation(LoadInformation officeLoadInformation) {
		this.officeLoadInformation = officeLoadInformation;
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
