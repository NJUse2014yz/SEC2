package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * 负责车辆管理的逻辑控制
 * @author 周聪
 *
 */
public class CarController implements CarBlService{

	@Override
	public ArrayList<CarVO> getAll() {
		Car car=new Car();
		ArrayList<CarVO> list=car.getAll();
		return list;
	}

	@Override
	public CarVO getSingle(String id) {
		Car car=new Car();
		return car.getSingle(id);
	}

	@Override
	public ResultMessage add(CarVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modify(CarVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
