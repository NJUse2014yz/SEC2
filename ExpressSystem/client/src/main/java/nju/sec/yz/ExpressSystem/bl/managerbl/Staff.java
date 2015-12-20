package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.accountbl.Initialable;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.StaffPO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.UserVO;

/**
 * 人员的领域模型对象
 * 
 * @author 周聪
 * @update sai
 */
public class Staff implements Initialable<StaffVO, StaffPO> {
	private StaffDataService data;

	public Staff() {
		try {
			data = DatafactoryProxy.getStaffDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	public ResultMessage addStaff(StaffVO sv) {
		ResultMessage message = null;
		// 验证information
		String validresult = isValid(sv);
		if (!validresult.equals("success")) {
			return new ResultMessage(Result.FAIL, validresult);
		}
		// 创建PO并保存
		try {
			String loginId = createLoginId(sv);

			/*
			 * 保存个人信息
			 */
			StaffPO po = changeVOToPO(sv);
			po.setLoginId(loginId);
			message = data.insert(po);
			if (message.getResult() == Result.FAIL)
				return message;

			message = saveLoginId(loginId, sv);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}
	
	/**
	 * 保存个人账号
	 */
	private ResultMessage saveLoginId(String loginId,StaffVO vo){
		String password=loginId;//初始密码和账号相同
		UserVO userInfo=new UserVO(loginId, vo.getName(), password, vo.getPower());
		User user=new User();
		ResultMessage message=user.add(userInfo);
		if(message.getResult()==Result.FAIL)
			return new ResultMessage(Result.FAIL,"添加员工账号时出现冲突，请联系系统管理员");
		
		return new ResultMessage(Result.SUCCESS);
	}
	
	/**
	 * 生成个人账号
	 */
	private String createLoginId(StaffVO vo){
		AgencyInfo agencyService=new Transit();
		String agencyId=agencyService.getId(vo.getAgency());
		String loginId="";
		//总公司人员账号前面没有机构id
		if(agencyId!=null)
			loginId=loginId+agencyId;
		loginId=loginId+vo.getPower().str+vo.getId();//机构id+x+三位数字id
		return loginId;
	}

	public ResultMessage deleteStaff(String id) {
		ResultMessage result = null;
		// 调用data层方法,验证id是否存在
		try {
			result=data.delete(id);
			if(result.getResult()==Result.FAIL)
				return result;
			//删除账户
			User user=new User();
			user.del(id);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return result;
	}

	public ResultMessage modifyStaff(StaffVO sv) {
		ResultMessage message = null;
		// 验证改过之后的vo
		String validresult = isValid(sv);
		if (!validresult.equals("success"))
			return new ResultMessage(Result.FAIL, validresult);
		// vo转po,数据库更新po
		StaffPO po = changeVOToPO(sv);
		try {
			message = data.update(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return message;
	}

	public StaffVO observeStaff(String id) {
		StaffVO vo = null;
		try {
			StaffPO po = data.find(id);
			if (po == null)
				return null;
			vo = show(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return vo;
	}

	public ArrayList<StaffVO> observeStaff() {
		ArrayList<StaffPO> listPO = null;
		ArrayList<StaffVO> listVO = new ArrayList<StaffVO>();
		// 获取数据库中的userpo列表
		try {
			listPO = data.findAll();
			if (listPO == null)
				return null;
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		// 将userpo列表转换成uservo列表
		for (int i = 0; i < listPO.size(); i++) {
			StaffVO vo = show(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	@Override
	public StaffVO show(StaffPO po) {
		String name = po.getName();
		String id = po.getId();
		Status power = po.getPower();
		String agency = po.getAgency();
		StaffVO vo = new StaffVO(name, id, power, agency,po.getLoginId());
		return vo;
	}

	@Override
	public StaffPO changeVOToPO(StaffVO sv) {
		String name = sv.getName();
		String id = sv.getId();
		Status power = sv.getPower();
		String agency = sv.getAgency();
		StaffPO po = new StaffPO(name, id, power, agency);
		return po;
	}

	private String isValid(StaffVO sv) {
		String id = sv.getId();

		if (!isId(id))
			return "人员编号是三位数字哦~";
		return "success";
	}

	private boolean isId(String id) {
		if (!ValidHelper.isNumber(id))
			return false;
		int len = id.length();
		/**
		 * @author cong
		 * 人员编号改为三位数字
		 */
		if(len!=Status.LENGTH_OF_NUMBER)
			return false;
		return true;
	}

	@Override
	public ResultMessage init(List<StaffVO> staffs) {
		ResultMessage message = new ResultMessage(Result.FAIL);

		List<StaffPO> pos = new ArrayList<>();
		for (StaffVO staff : staffs) {
			String validResult = isValid(staff);
			if (!validResult.equals("success"))
				return new ResultMessage(Result.FAIL, "第" + (staffs.indexOf(staff) + 1) + "个职员信息的 " + validResult);
			String loginId=createLoginId(staff);
			StaffPO po = this.changeVOToPO(staff);
			po.setLoginId(loginId);
			pos.add(po);
		}

		try {
			message = data.init(pos);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	// public void test(){
	// this.addStaff(new StaffVO("小周","D101",Status.DELIVER,"Position"));
	// StaffVO vo=this.observeStaff("D101");
	// System.out.println(vo.getId());
	// }
	// public static void main(String[] args) {
	// Staff staff=new Staff();
	// staff.test();
	// }

}
