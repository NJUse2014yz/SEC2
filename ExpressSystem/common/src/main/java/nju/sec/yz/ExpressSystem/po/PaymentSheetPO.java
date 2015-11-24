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
import nju.sec.yz.ExpressSystem.common.PaymentInformation;

public class PaymentSheetPO extends ReceiptPO{
	private PaymentInformation paymentInformation;
	private String barIds;
	
	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}
	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	public String getBarIds() {
		return barIds;
	}
	public void setBarIds(String barIds) {
		this.barIds = barIds;
	}
}
