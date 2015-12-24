package nju.sec.yz.ExpressSystem.blservice.userBlService;

/**
 * rename
 */
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public interface UserBlService {
	/**
	 * 用户登录
	 * 前置条件	Id符合格式要求，password符合输入规范
	 * 后置条件	查找是否存在相应的user，根据password返回验证结果
	 */
	public ResultMessage login(String id,String password);
	
	/**
	 * 获得所有用户信息
	 * @return
	 */
	public ArrayList<UserVO> getAll();
	
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public UserVO getSingle(String id);
	
	/**
	 * 添加用户
	 * @param vo
	 * @return
	 */
	public ResultMessage add(UserVO vo);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public ResultMessage del(String id);
	
	/**
	 * 修改用户信息
	 * @param vo
	 * @return
	 */
	public ResultMessage modify(UserVO vo);
}
