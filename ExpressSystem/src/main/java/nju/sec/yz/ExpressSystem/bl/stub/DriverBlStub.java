package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class DriverBlStub implements DriverBlService{

	@Override
	public ArrayList<DriverVO> getAll() {
		// TODO 自动生成的方法存根
		DriverVO dvo=new DriverVO("025001A020","许贺","19680304","3214631968030400254","15483794533",Sex.MALE,"6年");
		ArrayList<DriverVO> list=new ArrayList<DriverVO>();
		list.add(dvo);
		return list;
	}

	@Override
	public DriverVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return new DriverVO("025001A020","许贺","19680304","3214631968030400254","15483794533",Sex.MALE,"6年");
	}

	@Override
	public ResultMessage add(DriverVO vo) {
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
