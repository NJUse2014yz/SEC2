package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBLService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class UserBLServiceStub implements UserBLService {

	@Override
	public ResultMessage login(String id, String password) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<UserVO> getAll() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public UserVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage modify(UserVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
