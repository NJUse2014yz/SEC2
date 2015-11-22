package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 
 * @author xiaosaisai
 * 机构管理-增删改查
 */
public interface AgencyBlService {
	public ResultMessage addTransit (TransitVO av);
	
	public ResultMessage deleteTransit (String id);
	
	public ResultMessage modifyTransit (TransitVO av);
	
	public TransitVO observeTransit (String id);
	
	public ArrayList<TransitVO> observeAllTransit ();
	
	public ResultMessage addPosition(PositionVO av);
	
	public ResultMessage deletePosition(String id);
}
