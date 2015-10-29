package nju.sec.yz.ExpressSystem.po;
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
import nju.sec.yz.ExpressSystem.common.LoadInformation;

public class TransitLoadSheetPO extends ReceiptPO{
	private LoadInformation transitLoadInformation;
	private ArrayList<String> barIds;
	
	public LoadInformation getTransitLoadInformation() {
		return transitLoadInformation;
	}
	public void setTransitLoadInformation(LoadInformation transitLoadInformation) {
		this.transitLoadInformation = transitLoadInformation;
	}
	public ArrayList<String> getBarIds() {
		return barIds;
	}
	public void setBarIds(ArrayList<String> barIds) {
		this.barIds = barIds;
	}
}
