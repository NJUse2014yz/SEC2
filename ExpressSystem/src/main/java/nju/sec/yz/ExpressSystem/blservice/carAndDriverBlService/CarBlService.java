package nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.UserPO;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * @author xiaosaisai
 * @change YU Fan
 *	车辆信息管理
 */
public interface CarBlService {
	public ArrayList<CarVO> getAll();
	
	public CarVO getSingle(String id);
	
	public ResultMessage add(CarVO vo);
	
	public ResultMessage del(String id);
	
	public ResultMessage modify(CarVO vo);
	
}
