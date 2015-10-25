package nju.sec.yz.ExpressSystem.blservice.managerBlService;
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
