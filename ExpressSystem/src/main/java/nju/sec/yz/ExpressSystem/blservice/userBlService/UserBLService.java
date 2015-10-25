package nju.sec.yz.ExpressSystem.blservice.userBlService;


import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyV;

public interface UserBLService {
	
	public ResultMessage login(String id,String password);
	
	public ArrayList<AgencyV> getAll();
	
	public AgencyV getSingle(String id);
	
	public ResultMessage add(AgencyV vo);
	
	public ResultMessage del(String id);
	
	public ResultMessage modify(AgencyV vo);
}
