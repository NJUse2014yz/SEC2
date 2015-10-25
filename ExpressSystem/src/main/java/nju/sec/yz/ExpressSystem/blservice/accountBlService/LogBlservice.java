package nju.sec.yz.ExpressSystem.blservice.accountBlService;

public interface LogBlservice {
	//显示所有日志
		public List<LogVO> getAll();
		//启动查询单个日志
		public LogVO getSingle(int i);
		//启动查询某时间段的日志
		public List<LogVO> getByTime(String startTime,String endTime);
		
}
