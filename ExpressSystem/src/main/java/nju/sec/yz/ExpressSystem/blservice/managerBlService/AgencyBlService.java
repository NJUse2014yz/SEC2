package nju.sec.yz.ExpressSystem.blservice.managerBlService;
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
