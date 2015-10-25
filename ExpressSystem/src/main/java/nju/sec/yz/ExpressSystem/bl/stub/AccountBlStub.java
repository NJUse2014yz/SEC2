package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;

/**
 * 
 * @author xiaosaisai
 * 对账户进行增删改查的接口实现桩
 *
 */
public class AccountBlServiceStub implements AccountBlService{

	@Override
	public ResultMessage addAccount(){
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteAccount(String id) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modifyAccount(AccountVO av) {
		return ResultMessage.SUCCESS;
	}

	@Override
	public AccountVO observeAccount(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<AccountVO> observeList() {
		AccountVO avo1 = new AccountVO(1, "供应商1", false);
		AccountVO avo2 = new AccountVO(2, "供应商2", false);
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		list.add(avo1);
		list.add(avo2);
		return  list;
	}
	
	}
	
	
