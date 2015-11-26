package nju.sec.yz.ExpressSystem.po;

import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
import nju.sec.yz.ExpressSystem.common.TransitInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitFlightSheetPO extends ReceiptPO{
	private TransitFlightInformation transitFlightInformation;

	public TransitFlightInformation getTransitInformation() {
		return transitFlightInformation;
	}
	public void setTransitInformation(TransitFlightInformation transitFlightInformation) {
		this.transitFlightInformation = transitFlightInformation;
	}
}
