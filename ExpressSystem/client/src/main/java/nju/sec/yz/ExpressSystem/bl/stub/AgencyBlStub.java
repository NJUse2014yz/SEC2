package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;

public class AgencyBlStub implements AgencyBlService {

	@Override
	public ResultMessage addAgency(AgencyVO av) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage deleteAgency(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage modifyAgency(AgencyVO av) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AgencyVO observeAgency(String id) {
		// TODO 自动生成的方法存根
		return new AgencyVO("南京", "12", "顺丰快递");
	}

	@Override
	public ArrayList<AgencyVO> observeAllAgency() {
		AgencyVO avo1 = new AgencyVO("南京", "12", "顺丰快递");
		AgencyVO avo2 = new AgencyVO("上海","11","京东");
		ArrayList<AgencyVO> list = new ArrayList<AgencyVO>();
		list.add(avo1);
		list.add(avo2);
		return  list;
	}

}
