package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.accountbl.Initialable;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * 汽车信息的领域模型对象
 * 
 * @author 周聪
 */
public class Car implements Initialable<CarVO, CarPO> {

	private CarDataService carData;

	String positionId = this.getCurrentPosition();

	public Car() {
		try {
			carData = DatafactoryProxy.getCarDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	public ArrayList<CarVO> getAll() {

		ArrayList<CarPO> listPO = null;
		ArrayList<CarVO> listVO = new ArrayList<CarVO>();
		// 获取数据库中的userpo列表
		try {
			listPO = carData.findAll(positionId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		// 将userpo列表转换成uservo列表
		for (int i = 0; i < listPO.size(); i++) {
			CarVO vo = show(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	public CarVO getSingle(String id) {

		// 只能查询本营业厅车辆
		if (!id.contains(positionId))
			return null;
		CarVO vo = null;
		try {
			CarPO po = carData.find(id);
			if (po == null)
				return null;
			vo = show(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * 获得当前用户所属营业厅 业务员只能操作本营业厅车辆
	 */
	private String getCurrentPosition() {
		UserInfo userService = new User();
		String userId = userService.getCurrentID();
		if (userId == null || userId.contains("C"))
			return null;
		return userId.split("C")[0];

	}

	public ResultMessage add(CarVO vo) {
		ResultMessage message = null;
		// 验证information
		String validresult = isValid(vo);
		if (!validresult.equals("success")) {
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL, validresult);
		}
		// 创建PO并保存
		try {
			CarPO po = changeVOToPO(vo);
			message = carData.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	public ResultMessage del(String id) {
		ResultMessage result = null;

		// 只能操作本营业厅车辆
		if (!id.contains(positionId))
			return new ResultMessage(Result.FAIL,"id不正确~");

		// 调用data层方法,验证id是否存在
		try {
			result = carData.delete(id);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return result;
	}

	public ResultMessage modify(CarVO vo) {
		ResultMessage message = null;
		// 验证改过之后的vo
		String validresult = isValid(vo);
		if (!validresult.equals("success"))
			return new ResultMessage(Result.FAIL, validresult);
		// vo转po,数据库更新po
		CarPO po = changeVOToPO(vo);
		try {
			message = carData.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	private String isValid(CarVO vo) {
		String id = vo.getId();
		String number = vo.getNumber();
		String time = vo.getBuytime();
		String dipan = vo.getDipan();
		String machine = vo.getMechine();
		if (!isId(id))
			return "看看车辆ID输对了没哦";
		if (!isNumber(number))
			return "看看车牌号输对了没哦";
		if (!ValidHelper.isBeforeDate(time))
			return "购买时间是我们过去的日子哦";
		if (!ValidHelper.isNumber(dipan))
			return "底盘号输入错误哦";
		if (!ValidHelper.isNumber(machine))
			return "发动机号输入错误哦";
		return "success";
	}

	private boolean isNumber(String number) {
		if (number.length() != 7)
			return false;
		char a = number.charAt(0);
		char b = number.charAt(1);
		if (a != '京' && a != '津' && a != '冀' && a != '晋' && a != '蒙' && a != '辽' && a != '吉' && a != '琼' && a != '黑'
				&& a != '沪' && a != '苏' && a != '浙' && a != '皖' && a != '闽' && a != '赣' && a != '新' && a != '鲁'
				&& a != '豫' && a != '鄂' && a != '湘' && a != '粤' && a != '桂' && a != '渝' && a != '青' && a != '川'
				&& a != '黔' && a != '云' && a != '藏' && a != '陕' && a != '甘' && a != '宁') {
			return false;
		}

		if (b < 'A' || b > 'Z')
			return false;
		for (int i = 2; i < number.length(); i++) {
			char temp = number.charAt(i);
			if ((!(temp >= 'A' && temp <= 'Z')) && (!(temp >= '0' && temp <= '9')))
				return false;
		}
		return true;
	}

	/**
	 * 验证车辆id 中转单要用到
	 */
	public boolean isId(String id) {
		if (!id.contains("B"))
			return false;
		String strs[] = id.split("B");
		if (strs.length != 2)
			return false;
		if (!ValidHelper.isNumber(strs[0]))
			return false;
		if (strs[0].equals(positionId))
			return false;
		if (!ValidHelper.isNumber(strs[1]))
			return false;
		if (strs[1].length() != 3)
			return false;
		return true;
	}

	@Override
	public CarVO show(CarPO po) {
		String id = po.getId();
		String number = po.getNumber();
		String buytime = po.getBuytime();
		String mechine = po.getMechine();
		String dipan = po.getDipan();
		CarVO vo = new CarVO(id, number, buytime, mechine, dipan);
		int workTime = calculateTime(buytime);
		vo.setWorktime(workTime);
		return vo;
	}

	private int calculateTime(String buytime) {
		int dateToInt = Integer.parseInt(buytime.substring(0, 4));
		String now = TimeTool.getDate();
		int nowToInt = Integer.parseInt(now.substring(0, 4));
		return nowToInt - dateToInt;
	}

	@Override
	public CarPO changeVOToPO(CarVO vo) {
		String id = vo.getId();
		String number = vo.getNumber();
		String time = vo.getBuytime();
		String mechine = vo.getMechine();
		String dipan = vo.getDipan();
		CarPO po = new CarPO(id, number, time, mechine, dipan);
		int workTime = calculateTime(time);
		po.setWorktime(workTime);
		return po;
	}

	@Override
	public ResultMessage init(List<CarVO> cars) {
		ResultMessage message = new ResultMessage(Result.FAIL);

		List<CarPO> pos = new ArrayList<>();
		for (CarVO car : cars) {
			String validResult = isValid(car);
			if (!validResult.equals("success"))
				return new ResultMessage(Result.FAIL, cars.indexOf(car) + " " + validResult);

			CarPO po = this.changeVOToPO(car);
			pos.add(po);
		}

		try {
			message = carData.init(pos);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	/*
	 * public void test(){ this.add(new CarVO("025001001", "苏A23466",
	 * "20131212","hh" , "hh", "20121212")); CarVO
	 * vo=this.getSingle("025001001"); System.out.println(vo.getNumber()); }
	 */
}
