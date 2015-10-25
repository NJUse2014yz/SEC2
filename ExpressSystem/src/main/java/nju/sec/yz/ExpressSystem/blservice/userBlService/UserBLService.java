package nju.sec.yz.ExpressSystem.blservice.userBlService;

import java.awt.List;

public interface UserBLService {
	
	ResultMessage login(String id,String password);
	
	List<UserVO> getAll();
	
	UserVO getSingle(String id);
	
	ResultMessage add(UserVO vo);
	
	ResultMessage del(String id);
	
	ResultMessge modify(UserVO vo);
}
