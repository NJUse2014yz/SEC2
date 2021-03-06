package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

/**
 * 司机信息的领域模型对象
 * 
 * @author 周聪
 *
 */
public class Driver {

	private DriverDataService data;

	private String positionId = this.getCurrentPosition();

	public Driver() {
		try {
			data = DatafactoryProxy.getDriverDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	public ArrayList<DriverVO> getAll() {
		ArrayList<DriverPO> listPO = null;
		ArrayList<DriverVO> listVO = new ArrayList<DriverVO>();
		// 获取数据库中的userpo列表
		try {
			listPO = data.findAll(positionId);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		// 将userpo列表转换成uservo列表
		for (int i = 0; i < listPO.size(); i++) {
			DriverVO vo = show(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	/**
	 * 获得当前用户所属营业厅 业务员只能操作本营业厅车辆
	 */
	private String getCurrentPosition() {
		UserInfo userService = new User();
		String userId = userService.getCurrentID();
		if (userId == null || !userId.contains("C"))
			return null;
		return userId.split("C")[0];

	}

	public DriverVO getSingle(String id) {
		DriverVO vo = null;
		// 只能查询本营业厅
		if (!id.contains(positionId))
			return null;
		try {
			DriverPO po = data.find(id);
			if (po == null)
				return null;
			vo = show(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return vo;
	}

	public ResultMessage add(DriverVO vo) {
		ResultMessage message = null;
		// 验证information
		String validresult = isValid(vo);
		if (!validresult.equals("success")) {
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL, validresult);
		}
		// 创建PO并保存
		try {
			DriverPO po = changeVOToPO(vo);
			message = data.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	public ResultMessage del(String id) {
		ResultMessage result = null;
		// 调用data层方法,验证id是否存在
		try {
			result = data.delete(id);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return result;
	}

	public ResultMessage modify(DriverVO vo) {
		ResultMessage message = null;
		// 验证改过之后的vo
		String validresult = isValid(vo);
		if (!validresult.equals("success"))
			return new ResultMessage(Result.FAIL, validresult);
		// vo转po,数据库更新po
		DriverPO po = changeVOToPO(vo);
		try {
			message = data.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	private String isValid(DriverVO vo) {
		String id = vo.getId();
		String birthDate = vo.getBirthDate();
		String personID = vo.getPersonID();
		String phoneNumber = vo.getPhoneNumber();
		String licenseDeadLine = vo.getLicenseDeadLine();
		if (!isId(id))
			return "看看ID输对了没";
		if (!isBeforeDate(birthDate))
			return "出生日期不符合日期格式啊";
		if (!isPersonId(personID))
			return "身份证输错了哦";
		if (!isPhoneNumber(phoneNumber))
			return "手机号码不是11位数字吗，你这";
		if (!isLaterDate(licenseDeadLine))
			return "行驶证期限不符合日期格式哦";
		return "success";
	}

	private boolean isPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() != 11)
			return false;
		char array[] = phoneNumber.toCharArray();
		for (char a : array)
			if (a < '0' || a > '9')
				return false;
		return true;
	}

	private boolean isPersonId(String personID) {
		if (personID.length() != 18)
			return false;
		char array[] = personID.toCharArray();
		for (int i = 0; i < 17; i++)
			if (array[i] < '0' || array[i] > '9')
				return false;
		if ((array[17] < '0' || array[17] > '9') && (array[17] != 'x' || array[17] != 'X'))
			return false;
		return true;
	}

	private boolean isBeforeDate(String date) {
		if (ValidHelper.isBeforeDate(date))
			return true;
		return false;
	}

	private boolean isLaterDate(String date) {
		if (!ValidHelper.isLaterDate(date))
			return false;
		return true;
	}

	private boolean isId(String id) {
		if (!id.contains("A"))
			return false;
		String strs[] = id.split("A");
		if (strs.length != 2)
			return false;
		if (!ValidHelper.isNumber(strs[0]))
			return false;
		if (!strs[0].equals(positionId))
			return false;
		if (!ValidHelper.isNumber(strs[1]))
			return false;
		if (strs[1].length() != 3)
			return false;
		return true;
	}

	public DriverVO show(DriverPO po) {
		String id = po.getId();
		String name = po.getName();
		String birthDate = po.getBirthDate();
		String personID = po.getPersonID();
		String phoneNumber = po.getPhoneNumber();
		String agency=po.getAgency();
		Sex sex = po.getSex();
		String licenseDeadLine = po.getLicenseDeadLine();
		DriverVO vo = new DriverVO(id, name, birthDate, personID, phoneNumber, sex,agency, licenseDeadLine);
		return vo;
	}

	public DriverPO changeVOToPO(DriverVO vo) {
		String id = vo.getId();
		String name = vo.getName();
		String birthDate = vo.getBirthDate();
		String personID = vo.getPersonID();
		String phoneNumber = vo.getPhoneNumber();
		String agency=vo.getAgency();
		Sex sex = vo.getSex();
		String licenseDeadLine = vo.getLicenseDeadLine();
		DriverPO po = new DriverPO(id, name, birthDate, personID, phoneNumber,sex,agency, licenseDeadLine);
		return po;
	}

	/*
	 * public void test(){ this.add(new DriverVO("0251234565", "mike",
	 * "19020123", "450333190201232345", "13456789023", Sex.MALE, "20161223"));
	 * DriverVO vo=this.getSingle("0251234565");
	 * System.out.println(vo.getName()); }
	 */

}