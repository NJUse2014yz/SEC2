package nju.sec.yz.ExpressSystem.blservice.userBlService;

/**
 * rename
 */
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public interface UserBlService {
	
	public ResultMessage login(String id,String password);
	
	public ArrayList<UserVO> getAll();
	
	public UserVO getSingle(String id);
	
	public ResultMessage add(UserVO vo);
	
	public ResultMessage del(String id);
	
	public ResultMessage modify(UserVO vo);
}
