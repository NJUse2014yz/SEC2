package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class PaymentMockObject {
	
	public ResultMessage approve(ReceiptVO vo) {
		System.out.println("handling an approved payment...");
		OutVO payment=(OutVO)vo;
		AccountMockObject account=new AccountMockObject();
		account.updatePayment(payment.getOutInformation().getAccount(),payment.getOutInformation().getNum());
		System.out.println("paid "+payment.getOutInformation().getNum()+" dollars");
		OutPO po=new OutPO(null, 0, null, null, null, null);
		save(po);
		return null;
	}
	
	/**
	 * 有付款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage save(OutPO po){
		System.out.println("saving a payment...");
		
		return null;
	}
}
