package nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService;

import java.util.ArrayList;
import nju.sec.yz.ExpressSystem.vo.DriverVO;
import nju.sec.yz.ExpressSystem.common.ResultMessage;

/**
 * @author xiaosaisai
 * @change YU Fan
 * 司机信息管理
 */
public interface DriverBlService {
	public ArrayList<DriverVO> getAll();
	
	public DriverVO getSingle(String id);
	
	public ResultMessage add(DriverVO vo);
	
	public ResultMessage del(String id);
	
	public ResultMessage modify(DriverVO vo);
}
