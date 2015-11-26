package nju.sec.yz.ExpressSystem.po;

import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransitInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitCarSheetPO extends ReceiptPO{
	private TransitCarInformation transitCarInformation;

	public TransitCarInformation getTransitInformation() {
		return transitCarInformation;
	}
	public void setTransitInformation(TransitCarInformation transitCarInformation) {
		this.transitCarInformation = transitCarInformation;
	}
}

