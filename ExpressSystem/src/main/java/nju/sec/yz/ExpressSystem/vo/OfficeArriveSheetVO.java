package nju.sec.yz.ExpressSystem.vo;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.ArriveInformation;

public class OfficeArriveSheetVO extends ReceiptVO{
	private ArriveInformation officeArrive;

	public ArriveInformation getOfficeArrive() {
		return officeArrive;
	}

	public void setOfficeArrive(ArriveInformation officeArrive) {
		this.officeArrive = officeArrive;
	}
}
