package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *中转中心装车单 
 */

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.LoadInformation;

public class TransitLoadSheetVO extends ReceiptVO{
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
