package nju.sec.yz.ExpressSystem.po;

import nju.sec.yz.ExpressSystem.common.TransitInformation;
import nju.sec.yz.ExpressSystem.common.TransitTrainInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class TransitTrainSheetPO extends ReceiptPO{
	private TransitTrainInformation transitTrainInformation;

	public TransitTrainInformation getTransitInformation() {
		return transitTrainInformation;
	}
	public void setTransitInformation(TransitTrainInformation transitTrainInformation) {
		this.transitTrainInformation = transitTrainInformation;
	}
}
