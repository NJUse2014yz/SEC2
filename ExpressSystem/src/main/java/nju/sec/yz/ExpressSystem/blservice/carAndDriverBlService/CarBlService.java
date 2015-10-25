package nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * @author xiaosaisai
 *	车辆信息管理
 */
public interface CarBlService {
	public ArrayList<CarVO> getAll();
	
	public CarVO getSingle(String id);
	
	public ResultMessage add(CarVo vo);
	
	public ResultMessage del(String id);
	
	public ResultMessage modify(CarVo vo);
	
}
