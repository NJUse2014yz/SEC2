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
	/**
	 * 增加人员信息，同时自动增加系统账户
	 * @param sv
	 * @return
	 */
	public ResultMessage addStaff (StaffVO sv);
	
	/**
	 * 删除人员信息
	 * @param id
	 * @return
	 */
	public ResultMessage deleteStaff (String id);
	
	/**
	 * 修改人员信息
	 * @param sv
	 * @return
	 */
	public ResultMessage modifyStaff (StaffVO sv);
	
	/**
	 * 按id查找人员
	 * @param id
	 * @return
	 */
	public StaffVO observeStaff (String id);
	
	/**
	 * 获得所有人员信息
	 * @return
	 */
	public ArrayList<StaffVO> observeStaff();
}
