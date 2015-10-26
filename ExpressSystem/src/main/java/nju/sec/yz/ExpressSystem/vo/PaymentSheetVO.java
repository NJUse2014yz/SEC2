package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 * 收款单
 */

import java.util.ArrayList;

/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.PaymentInformation;

public class PaymentSheetVO extends ReceiptVO{
	private PaymentInformation paymentInformation;
	private ArrayList<String> barIds;
	
	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}
	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	public ArrayList<String> getBarIds() {
		return barIds;
	}
	public void setBarIds(ArrayList<String> barIds) {
		this.barIds = barIds;
	}
	
}
