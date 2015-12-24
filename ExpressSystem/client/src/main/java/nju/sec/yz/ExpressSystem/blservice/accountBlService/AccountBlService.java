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
	
	/**
	 * add
	 * @param av
	 * @return
	 */
	public ResultMessage addAccount(AccountVO av);
	
	/**
	 * delete
	 * @param id
	 * @return
	 */
	public ResultMessage deleteAccount(String id);
	
	/**
	 * modify
	 * @param av
	 * @return
	 */
	public ResultMessage modifyAccount(AccountVO av);
	
	/**
	 * observe
	 * @param id
	 * @return
	 */
	public AccountVO observeAccount(String id);
	
	/**
	 * 显示账户列表
	 * @return
	 */
	public ArrayList<AccountVO> observeList();
	
	
	
}
