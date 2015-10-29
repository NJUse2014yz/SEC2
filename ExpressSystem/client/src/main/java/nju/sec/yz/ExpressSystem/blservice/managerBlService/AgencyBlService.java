package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;

/**
 * 
 * @author xiaosaisai
 * 机构管理-增删改查
 */
public interface AgencyBlService {
	public ResultMessage addAgency (AgencyVO av);
	
	public ResultMessage deleteAgency (String id);
	
	public ResultMessage modifyAgency (AgencyVO av);
	
	public AgencyVO observeAgency (String id);
	
	public ArrayList<AgencyVO> observeAllAgency ();
}
