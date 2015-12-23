package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.StaffVO;

public class StaffBlStub implements StaffBlService {

	@Override
	public ResultMessage addStaff(StaffVO sv) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage deleteStaff(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage modifyStaff(StaffVO sv) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public StaffVO observeStaff(String id) {
		// TODO 自动生成的方法存根
		return new StaffVO("刘强东", "s001", Status.MANAGER,"京东","S001");
	}

	@Override
	public ArrayList<StaffVO> observeStaff() {
		StaffVO svo=new StaffVO("刘强东", "s001", Status.MANAGER,"京东", "S001");
		ArrayList<StaffVO> list = new ArrayList<StaffVO>();
		list.add(svo);
		return  list;
	}

}
