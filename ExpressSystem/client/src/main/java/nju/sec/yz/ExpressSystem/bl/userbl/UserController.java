package nju.sec.yz.ExpressSystem.bl.userbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

/**
 * 负责实现对应于用户管理界面所需的方法
 * @author 周聪
 *
 */
public class UserController implements UserBlService{

	@Override
	public ResultMessage login(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getSingle(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modify(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}