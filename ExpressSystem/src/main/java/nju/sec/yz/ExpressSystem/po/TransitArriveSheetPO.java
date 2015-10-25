package nju.sec.yz.ExpressSystem.po;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.ArriveInformation;

public class TransitArriveSheetPO extends ReceiptPO{
	private ArriveInformation transitArriveInformation;

	public ArriveInformation getTransitArriveInformation() {
		return transitArriveInformation;
	}

	public void setTransitArriveInformation(
			ArriveInformation transitArriveInformation) {
		this.transitArriveInformation = transitArriveInformation;
	}
}
