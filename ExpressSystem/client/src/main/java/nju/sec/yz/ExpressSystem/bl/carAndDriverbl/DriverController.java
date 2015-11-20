package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.DriverVO;
/**
 * 负责司机管理的逻辑控制
 * @author 周聪
 *
 */
public class DriverController implements DriverBlService{

	@Override
	public ArrayList<DriverVO> getAll() {
		Driver driver=new Driver();
		ArrayList<DriverVO> list=driver.getAll();
		return list;
	}

	@Override
	public DriverVO getSingle(String id) {
		Driver driver=new Driver();
		return driver.getSingle(id);
	}

	@Override
	public ResultMessage add(DriverVO vo) {
		Driver driver=new Driver();
		ResultMessage resultMessage=driver.add(vo);
		return resultMessage;
	}

	@Override
	public ResultMessage del(String id) {
		Driver driver=new Driver();
		ResultMessage resultMessage=driver.del(id);
		return resultMessage;
	}

	@Override
	public ResultMessage modify(DriverVO vo) {
		Driver driver=new Driver();
		ResultMessage resultMessage=driver.modify(vo);
		return resultMessage;
	}

}
