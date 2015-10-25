package nju.sec.yz.ExpressSystem.blservice.userBlService;


import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public interface UserBLService {
	
	ResultMessage login(String id,String password);
	
	ArrayList<UserVO> getAll();
	
	UserVO getSingle(String id);
	
	ResultMessage add(UserVO vo);
	
	ResultMessage del(String id);
	
	ResultMessage modify(UserVO vo);
}
