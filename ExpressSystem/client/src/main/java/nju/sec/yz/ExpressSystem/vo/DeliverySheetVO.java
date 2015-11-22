package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *派件但
 */

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;

public class DeliverySheetVO extends ReceiptVO{
	private DeliveryInformation deliveryInformation;
//	private ArrayList<String> barIds;
	
	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}
	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
//	public ArrayList<String> getBarIds() {
//		return barIds;
//	}
//	public void setBarIds(ArrayList<String> barIds) {
//		this.barIds = barIds;
//	}
}
