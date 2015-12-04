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
	
	
	public DeliveryInformation getDeliveryInformation() {
		return deliveryInformation;
	}
	public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
		this.deliveryInformation = deliveryInformation;
	}
		
}
