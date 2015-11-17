package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

/**
 * 
 * @author xiaosaisai
 * 对账户进行增删改查的接口实现桩
 *
 */
public class AccountBlStub implements AccountBlService{

	@Override
	public ResultMessage addAccount(AccountVO av){
		return null;
	}

	@Override
	public ResultMessage deleteAccount(String id) {
		return null;
	}

	@Override
	public ResultMessage modifyAccount(AccountVO av) {
		return null;
	}

	@Override
	public AccountVO observeAccount(String id) {
		// TODO 自动生成的方法存根
		return new AccountVO("八方达物流有限公司", 10000);
	}

	@Override
	public ArrayList<AccountVO> observeList() {
		AccountVO avo1 = new AccountVO("八方达物流有限公司", 10000);
		AccountVO avo2 = new AccountVO("八方达物流有限公司", 20000);
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		list.add(avo1);
		list.add(avo2);
		return  list;
	}
	
}
	
	
