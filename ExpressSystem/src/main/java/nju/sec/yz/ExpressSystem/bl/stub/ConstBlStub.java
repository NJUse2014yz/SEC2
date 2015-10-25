package nju.sec.yz.ExpressSystem.bl.stub;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ConstVO;

public class ConstBlStub implements ConstBlService {

	@Override
	public ConstVO observeConst() {
		// TODO 自动生成的方法存根
		return new ConstVO();
	}

	@Override
	public ResultMessage modifyConst(ConstVO cv) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
