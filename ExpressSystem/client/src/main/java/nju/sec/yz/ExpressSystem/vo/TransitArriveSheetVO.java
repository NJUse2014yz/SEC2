package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *中转中心到达单 
 */
import nju.sec.yz.ExpressSystem.common.ArriveInformation;

public class TransitArriveSheetVO extends ReceiptVO{
	private ArriveInformation transitArriveInformation;
	private String transitId;
	

	public String getTransitId() {
		return transitId;
	}

	public void setTransitId(String transitId) {
		this.transitId = transitId;
	}

	
	
	public ArriveInformation getTransitArriveInformation() {
		return transitArriveInformation;
	}

	public void setTransitArriveInformation(
			ArriveInformation transitArriveInformation) {
		this.transitArriveInformation = transitArriveInformation;
	}
}
