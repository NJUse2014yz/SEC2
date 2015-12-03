package nju.sec.yz.ExpressSystem.blservice.accountBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.vo.LogVO;

public interface LogBlService {
		//显示所有日志
		public ArrayList<LogVO> getAll();
		
		//启动查询某天的日志
		public ArrayList<LogVO> getByTime(String Time);
}
