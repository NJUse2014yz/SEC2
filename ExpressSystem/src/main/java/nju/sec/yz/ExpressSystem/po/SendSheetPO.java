package nju.sec.yz.ExpressSystem.po;
/**
 * 
 * @author YU Fan
 *
 */
import nju.sec.yz.ExpressSystem.common.SendInformation;

public class SendSheetPO extends ReceiptPO{
	private SendInformation sendInformation;

	public SendInformation getSendInformation() {
		return sendInformation;
	}

	public void setSendInformation(SendInformation sendInformation) {
		this.sendInformation = sendInformation;
	}
}
