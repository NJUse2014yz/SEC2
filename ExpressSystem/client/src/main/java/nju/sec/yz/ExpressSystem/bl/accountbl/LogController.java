package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.vo.LogVO;
/**
 * 负责日志的逻辑控制
 * @author 周聪
 * @update sai
 */
public class LogController implements LogBlService{

	@Override
	public ArrayList<LogVO> getAll() {
		Log log=new Log();
		ArrayList<LogVO> list=log.getAll();
		return list;
	}

	@Override
	public LogVO getSingle(int i) {
		Log log=new Log();
		LogVO logVo=log.getSingle(i);
		return logVo;
	}

	@Override
	public ArrayList<LogVO> getByTime(String startTime, String endTime) {
		Log log=new Log();
		ArrayList<LogVO> list=log.getByTime(startTime, endTime);
		return list;
	}

}
