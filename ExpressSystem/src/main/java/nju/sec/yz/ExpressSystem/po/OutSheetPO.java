package nju.sec.yz.ExpressSystem.po;

import nju.sec.yz.ExpressSystem.common.OutInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class OutSheetPO extends ReceiptPO{
	private OutInformation outInformation;

	public OutInformation getOutInformation() {
		return outInformation;
	}

	public void setOutInformation(OutInformation outInformation) {
		this.outInformation = outInformation;
	}
}
