package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.po.StaffPO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;

/**
 * 人员的领域模型对象
 * @author 周聪
 * @update sai
 */
public class Staff {
	private StaffDataService data;
	
	public Staff(){
		try {
			data=DatafactoryProxy.getStaffDataService();
		} catch (RemoteException e) {
			//TODO 远程异常
			e.printStackTrace();
		}
	}
	public ResultMessage addStaff(StaffVO sv) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(sv);
		if(!validresult.equals("success")){
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		try {
			StaffPO po=changeVoToPo(sv);
			message=data.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	public ResultMessage deleteStaff(String id) {
		ResultMessage result=null;
		//调用data层方法,验证id是否存在
		try {
			result=data.delete(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return result;
	}


	public ResultMessage modifyStaff(StaffVO sv) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(sv);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		StaffPO po=changeVoToPo(sv);
		try {
			message=data.update(po);
		} catch (RemoteException e){
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	public StaffVO observeStaff(String id) {
		StaffVO vo=null;
		try {
			StaffPO	po = data.find(id);
			if(po==null)
				return null;
			vo=changePoToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}

	
	public ArrayList<StaffVO> observeStaff() {
		ArrayList<StaffPO> listPO = null;
		ArrayList<StaffVO> listVO = new ArrayList<StaffVO>();
		//获取数据库中的userpo列表
		try {
			listPO=data.findAll();
			if(listPO==null)
				return null;
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			StaffVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}
	private StaffVO changePoToVo(StaffPO po) {
		String name=po.getName();
		String id=po.getId();
		Status power=po.getPower();
		String agency=po.getAgency();
		StaffVO vo=new StaffVO(name, id, power, agency);
		return vo;
	}
	private StaffPO changeVoToPo(StaffVO sv) {
		String name=sv.getName();
		String id=sv.getId();
		Status power=sv.getPower();
		String agency=sv.getAgency();
		StaffPO po=new StaffPO(name, id, power, agency);
		return po;
	}
	private String isValid(StaffVO sv) {
		String id=sv.getId();
		
		if(!isId(id))
			return "看看ID输对没";
		return "success";
	}
	private boolean isId(String id) {
		if(!ValidHelper.isNumber(id))
			return false;
		int len=id.length();
		if(len!=4&&len!=8&&len!=10)
			return false;
		return true;
	}
	
//	public void test(){
//		this.addStaff(new StaffVO("小周","D101",Status.DELIVER,"Position"));
//		StaffVO vo=this.observeStaff("D101");
//		System.out.println(vo.getId());
//	}
//	public static void main(String[] args) {
//		Staff staff=new Staff();
//		staff.test();
//	}

}
