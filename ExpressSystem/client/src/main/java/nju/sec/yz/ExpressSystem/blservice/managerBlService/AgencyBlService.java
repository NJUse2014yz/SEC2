package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 
 * @author xiaosaisai
 * 机构管理-增删改查
 */
public interface AgencyBlService {
	/**
	 * 添加中转中心
	 * @param av
	 * @return
	 */
	public ResultMessage addTransit (TransitVO av);
	
	/**
	 * 删除中转中心
	 * @param id
	 * @return
	 */
	public ResultMessage deleteTransit (String id);
	
	/**
	 * 修改中转中心
	 * @param av
	 * @return
	 */
	public ResultMessage modifyTransit (TransitVO av);
	
	/**
	 * 按id查找机构
	 * @param id
	 * @return
	 */
	public TransitVO observeTransit (String id);
	
	/**
	 * 按名称模糊搜索机构
	 * @param name
	 * @return
	 */
	public AgencyListVO observeTransitByName (String name);
	
	/**
	 * 获得所有机构信息
	 * @return
	 */
	public ArrayList<TransitVO> observeAllTransit ();
	
	/**
	 * 添加营业厅信息到所属中转中心
	 * @param av
	 * @return
	 */
	public ResultMessage addPosition(PositionVO av);
	
	/**
	 * 删除营业厅
	 * @param transitId  所属中转中心id
	 * @param id
	 * @return
	 */
	public ResultMessage deletePosition(String transitId,String id);
}
