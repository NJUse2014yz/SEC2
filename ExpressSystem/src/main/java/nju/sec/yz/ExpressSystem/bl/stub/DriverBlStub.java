package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class DriverBlStub implements DriverBlService{

	@Override
	public ArrayList<DriverVO> getAll() {
		// TODO 自动生成的方法存根
		return new ArrayList<DriverVO>();
	}

	@Override
	public DriverVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return new DriverVO();
	}

	@Override
	public ResultMessage add(DriverVo vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modify(DriverVO vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
