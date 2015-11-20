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

public class OfficeLoadSheetVO extends ReceiptVO{
	private LoadInformation officeLoadInformation;
	private ArrayList<String> barIds;
	
	
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
}
