package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;

/**
 * Account的领域模型对象
 * 负责账户管理的具体实现
 * @author 周聪
 */
public class Account {
	
	private AccountDataService accountData;
	
	public Account(){
		try {
			accountData=DatafactoryProxy.getAccountDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultMessage addAccount(AccountVO av) {
		// TODO Auto-generated method stub
		return null;
	}


	public ResultMessage deleteAccount(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modifyAccount(AccountVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public AccountVO observeAccount(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<AccountVO> observeList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 有付款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updatePayment(String account,int num){
		
		
		return null;
	}
	
	/**
	 * 有收款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updateCollection(String account,int num){
		
		return null;
	}
	
	/**
	 * 保存更新
	 */
	private ResultMessage modifyAccount(AccountPO ap) {
		// TODO Auto-generated method stub
		return null;
	}
}
