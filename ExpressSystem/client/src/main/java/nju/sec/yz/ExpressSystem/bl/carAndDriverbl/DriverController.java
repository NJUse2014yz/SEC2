package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.DriverVO;
import nju.sec.yz.ExpressSystem.vo.UserVO;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage add(DriverVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modify(DriverVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
