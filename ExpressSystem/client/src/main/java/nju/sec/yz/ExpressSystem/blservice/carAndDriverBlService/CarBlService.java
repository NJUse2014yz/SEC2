package nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * @author xiaosaisai
 * @change YU Fan
 *	车辆信息管理
 */

public interface CarBlService {
	/**
	 * 获得当前营业厅所有车辆信息
	 * @return
	 */
	public ArrayList<CarVO> getAll();
	
	/**
	 * 通过id查找本营业厅车辆
	 * @param id
	 * @return
	 */
	public CarVO getSingle(String id);
	
	/**
	 * 添加本营业厅车辆
	 * @param vo
	 * @return
	 */
	public ResultMessage add(CarVO vo);
	
	/**
	 * 删除本营业厅车辆
	 * @param id
	 * @return
	 */
	public ResultMessage del(String id);
	
	/**
	 * 修改本营业厅车辆信息
	 * @param vo
	 * @return
	 */
	public ResultMessage modify(CarVO vo);
	
}
