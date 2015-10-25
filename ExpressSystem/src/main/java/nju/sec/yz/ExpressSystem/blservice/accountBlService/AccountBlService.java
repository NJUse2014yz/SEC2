package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

/**
 * 
 * @author xiaosaisai
 * 对账户进行增删改查的接口
 *
 */
public interface AccountBlService{
	//add
	public ResultMessage addAccount(AccountVO av);
	//delete
	public ResultMessage deleteAccount(String id);
	//modify
	public ResultMessage modifyAccount(AccountVO av);
	//observe
	public AccountVO observeAccount(String id);
	//显示账户列表
	public ArrayList<AccountVO> observeList();
	
	
	
}
