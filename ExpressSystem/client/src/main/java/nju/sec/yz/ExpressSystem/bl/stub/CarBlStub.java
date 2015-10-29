package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CarVO;


public class CarBlStub implements CarBlService{

	@Override
	public ArrayList<CarVO> getAll() {
		// TODO 自动生成的方法存根
		CarVO cvo=new CarVO("025000A000","苏A 43295",3);
		ArrayList<CarVO> list=new ArrayList<CarVO>();
		list.add(cvo);
		return list;
	}

	@Override
	public CarVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return new CarVO("025000A000","苏A 43295",3);
	}

	@Override
	public ResultMessage add(CarVO vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modify(CarVO vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
