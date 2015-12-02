package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;

/**
 * Account的领域模型对象 负责账户管理的具体实现
 * 
 * @author 周聪
 */
public class Account {

	private AccountDataService accountData;

	public Account() {
		try {
			accountData = DatafactoryProxy.getAccountDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	public ResultMessage addAccount(AccountVO av) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(av);
		if(!validresult.equals("success")){
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		AccountPO po=changePoToVo();
		try {
			message=accountData.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	private AccountPO changePoToVo() {
		// TODO 自动生成的方法存根
		return null;
	}

	private String isValid(AccountVO av) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ResultMessage deleteAccount(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage modifyAccount(AccountVO av) {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountVO observeAccount(String name) {
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
	public ResultMessage updatePayment(String account, double d) {
		AccountVO vo = this.observeAccount(account);

		vo.setBalance(vo.getBalance() - d);

		ResultMessage message = this.modifyAccount(vo);

		return message;
	}

	/**
	 * 有收款单通过审批时，通过此方法更新账户
	 */
	public ResultMessage updateCollection(String account, double num) {
		AccountVO vo = this.observeAccount(account);

		vo.setBalance(vo.getBalance() + num);

		ResultMessage message = this.modifyAccount(vo);

		return message;
	}

}
