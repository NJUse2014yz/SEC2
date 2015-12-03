package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.vo.LogVO;

public class LogBlStub implements LogBlService {

	@Override
	public ArrayList<LogVO> getAll() {
		// TODO 自动生成的方法存根
		LogVO vo1=new LogVO("20151025", "getall", "李华");
		ArrayList<LogVO> list=new ArrayList<LogVO>();
		list.add(vo1);
		return list;
	}

	@Override
	public ArrayList<LogVO> getByTime(String time) {
		// TODO 自动生成的方法存根
		LogVO vo1=new LogVO("20151025", "getbytime", "李华");
		ArrayList<LogVO> list=new ArrayList<LogVO>();
		list.add(vo1);
		return list;
	}

}
