package nju.sec.yz.ExpressSystem.bl.tool;

import nju.sec.yz.ExpressSystem.bl.accountbl.Log;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.vo.LogVO;

/**
 * 新增日志的工具类
 * @author sai
 */
public class LogTool {
	public static void setLog(String string) {
		UserInfo user=new User();
		String userid=user.getCurrentID();
		LogVO vo=new LogVO(TimeTool.getDate(), string, userid);
		Log log=new Log();
		log.addLog(vo);
	}
}
