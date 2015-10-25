package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;
import nju.sec.yz.ExpressSystem.vo.AgencyV;

public class AgencyBlStub implements AgencyBlService {

	@Override
	public ResultMessage addAgency(AgencyVO av) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteAgency(String id) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modifyAgency(AgencyVO av) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public AgencyVO observeAgency(String id) {
		// TODO 自动生成的方法存根
		return new AgencyVO();
	}

	@Override
	public ArrayList<AgencyVO> observeAllAgency() {
		AgencyVO avo1 = new AgencyVO("s001","刘强东","jingdong",Power.MANAGER);
		AgencyVO avo2 = new AgencyVO("s002","章泽天","jingdong",Power.DELIVER);
		ArrayList<AgencyVO> list = new ArrayList<AgencyVO>();
		list.add(avo1);
		list.add(avo2);
		return  list;
	}

}
