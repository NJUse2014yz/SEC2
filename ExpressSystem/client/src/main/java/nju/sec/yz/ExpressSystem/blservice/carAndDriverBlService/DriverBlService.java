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
	/**
	 * 获得当前营业厅所有司机信息
	 * @return
	 */
	public ArrayList<DriverVO> getAll();
	
	/**
	 * 通过id查找本营业厅司机
	 * @param id
	 * @return
	 */
	public DriverVO getSingle(String id);
	
	/**
	 * 添加本营业厅司机
	 * @param vo
	 * @return
	 */
	public ResultMessage add(DriverVO vo);
	
	/**
	 * 删除本营业厅司机
	 * @param id
	 * @return
	 */
	public ResultMessage del(String id);
	
	/**
	 * 修改本营业厅司机信息
	 * @param vo
	 * @return
	 */
	public ResultMessage modify(DriverVO vo);
}
