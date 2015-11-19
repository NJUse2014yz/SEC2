package nju.sec.yz.ExpressSystem.vo;
/**
 * @author YU Fan
 * 收件信息
 */
import nju.sec.yz.ExpressSystem.common.ReceiveInformation;

public class ReceiveVO extends ReceiptVO{
	private ReceiveInformation receiveInformation;

	public ReceiveInformation getReceiveInformation() {
		return receiveInformation;
	}

	public void setReceiveInformation(ReceiveInformation receiveInformation) {
		this.receiveInformation = receiveInformation;
	}
}
