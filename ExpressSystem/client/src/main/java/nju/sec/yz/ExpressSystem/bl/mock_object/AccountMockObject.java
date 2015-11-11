package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;

public class AccountMockObject {
	public ResultMessage modifyAccount(AccountVO av) {
		
		return null;
	}
	
	/**
	 * 有付款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updatePayment(String account,int num){
		AccountPO po=new AccountPO(account, 100);
		System.out.println("The account "+po.getName()+"has "+po.getBalance()+" dollars");
		int balance=po.getBalance()-num;
		po.setBalance(balance);
		System.out.println("Now it has "+balance+" dollars");
		return null;
	}
}
