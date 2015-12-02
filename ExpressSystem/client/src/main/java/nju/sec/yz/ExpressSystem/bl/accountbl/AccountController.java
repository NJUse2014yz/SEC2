package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
/**
 * 负责账户管理的逻辑控制
 * @author 周聪
 * @update sai
 */
public class AccountController implements AccountBlService{

	@Override
	public ResultMessage addAccount(AccountVO av) {
		Account account=new Account();
		ResultMessage message=account.addAccount(av);
		return message;
	}

	@Override
	public ResultMessage deleteAccount(String id) {
		Account account=new Account();
		ResultMessage message=account.deleteAccount(id);
		return message;
	}

	@Override
	public ResultMessage modifyAccount(AccountVO av) {
		Account account=new Account();
		ResultMessage message=account.modifyAccount(av);
		return message;
	}

	@Override
	public AccountVO observeAccount(String id) {
		Account account=new Account();
		AccountVO vo=account.observeAccount(id);
		return vo;
	}

	@Override
	public ArrayList<AccountVO> observeList() {
		Account account=new Account();
		ArrayList<AccountVO> list=account.observeList();
		return list;
	}

}
