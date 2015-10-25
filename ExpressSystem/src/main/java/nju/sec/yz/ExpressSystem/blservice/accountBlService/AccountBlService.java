package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import nju.sec.yz.ExpressSystem.ResultMessage;

/**
 * 
 * @author xiaosaisai
 * 对账户进行增删改查的接口
 *
 */
public interface AccountService{
	//add
	public ResultMessage addAccount(AccountVO);
	//delete
	public ResultMessage deleteAccount(String id);
	//modify
	public ResultMessage modifyAccount(AccountVO av);
	//observe
	public AccountVO observeAccount(String id);
	//显示账户列表
	public ArrayList<AccountVO> observeList();
	
	
	
}