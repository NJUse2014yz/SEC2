package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBLService;
import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyV;

public class UserBLStub implements UserBLService {

	@Override
	public ResultMessage login(String id, String password) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<AgencyV> getAll() {
		AgencyV uvo1 = new AgencyV("s001","刘强东","jingdong",Power.MANAGER);
		AgencyV uvo2 = new AgencyV("s002","章泽天","jingdong",Power.DELIVER);
		ArrayList<AgencyV> list = new ArrayList<AgencyV>();
		list.add(uvo1);
		list.add(uvo2);
		return  list;
	}

	@Override
	public AgencyV getSingle(String id) {
		// TODO 自动生成的方法存根
		return new AgencyV("s001","刘强东","jingdong",Power.MANAGER);
	}

	@Override
	public ResultMessage add(AgencyV vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modify(AgencyV vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
