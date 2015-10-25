package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.vo.LogVO;

public class LogBlStub implements LogBlService {

	@Override
	public ArrayList<LogVO> getAll() {
		// TODO 自动生成的方法存根
		return new ArrayList<LogVO>();
	}

	@Override
	public LogVO getSingle(int i) {
		// TODO 自动生成的方法存根
		return new LogVO("20000525", "add", "李华");
	}

	@Override
	public ArrayList<LogVO> getByTime(String startTime, String endTime) {
		// TODO 自动生成的方法存根
		return new ArrayList<LogVO>();
	}

}
