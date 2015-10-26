package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.vo.LogVO;

public interface LogBlService2 {
		//显示所有日志
		public ArrayList<LogVO> getAll();
		//启动查询单个日志
		public LogVO getSingle(int i);
		//启动查询某时间段的日志
		public ArrayList<LogVO> getByTime(String startTime,String endTime);
		
}
