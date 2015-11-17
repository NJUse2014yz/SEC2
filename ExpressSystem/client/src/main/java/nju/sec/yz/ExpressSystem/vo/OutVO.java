package nju.sec.yz.ExpressSystem.vo;

import nju.sec.yz.ExpressSystem.common.OutInformation;
/**
 * 
 * @author 周聪
 * 出款单信息
 */
public class OutVO extends ReceiptVO{

	private OutInformation outInformation;

	public OutVO(String date, int num, String person, String account, String reason, String comments) {
		super();
		outInformation=new OutInformation();
		outInformation.setAccount(account);
		outInformation.setComments(comments);
		outInformation.setDate(date);
		outInformation.setNum(num);
		outInformation.setPerson(person);
		outInformation.setReason(reason);
	}

	public OutInformation getOutInformation() {
		return outInformation;
	}
	
	
	
}
