package nju.sec.yz.ExpressSystem.po;
/**
 * 
 * @author YU Fan
 * 收件单
 */

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;

public class DeliverySheetPO extends ReceiptPO{
	private DeliveryInformation deliveryInformation;
	private ArrayList<String> barIds;
	
	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}
	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
	public ArrayList<String> getBarIds() {
		return barIds;
	}
	public void setBarIds(ArrayList<String> barIds) {
		this.barIds = barIds;
	}	
}
