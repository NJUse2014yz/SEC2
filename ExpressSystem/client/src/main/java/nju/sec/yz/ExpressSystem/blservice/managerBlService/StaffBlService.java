package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.StaffVO;

/**
 * 
 * @author xiaosaisai
 * 人员管理--增删改查
 */
public interface StaffBlService {
	public ResultMessage addStaff (StaffVO sv);
	
	public ResultMessage deleteStaff (String id);
	
	public ResultMessage modifyStaff (StaffVO sv);
	
	public StaffVO observeStaff (String id);
	
	public ArrayList<StaffVO> observeStaff();
}
