package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.ArriveInformation;

public class TransitArriveSheetVO extends ReceiptVO{
	private ArriveInformation transitArriveInformation;

	public ArriveInformation getTransitArriveInformation() {
		return transitArriveInformation;
	}

	public void setTransitArriveInformation(
			ArriveInformation transitArriveInformation) {
		this.transitArriveInformation = transitArriveInformation;
	}
}
