package nju.sec.yz.ExpressSystem.bl.managerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.po.SalaryPO;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

/**
 * 薪水信息的领域模型
 * @author 周聪
 *
 */
public class Salary {
private SalaryDataService data;
	
	public Salary(){
		try {
			data=DatafactoryProxy.getSalaryDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	public ResultMessage modifySalary(SalaryVO sv) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(sv);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		SalaryPO po=changeVoToPo(sv);
		try{
			message=data.update(po);
		}catch(RemoteException e){
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	public ArrayList<SalaryVO> observeSalary() {
		ArrayList<SalaryPO> listPO = null;
		ArrayList<SalaryVO> listVO = new ArrayList<SalaryVO>();
		//获取数据库中的salarypo列表
		try {
			listPO=data.findAll();
			if(listPO==null)
				return null;
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			SalaryVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;

	}
	private SalaryVO changePoToVo(SalaryPO salaryPO) {
		SalaryImformation information=salaryPO.getSalaryImformation();
		SalaryVO vo=new SalaryVO(information);
		return vo;
	}
	
	private SalaryPO changeVoToPo(SalaryVO sv) {
		SalaryImformation information=sv.getSalaryImformation();
		SalaryPO po=new SalaryPO(information);
		return po;
	}
	private String isValid(SalaryVO sv) {
		SalaryImformation information=sv.getSalaryImformation();
		//Status power=information.getPower();
		int salary= information.getSalary();
		if(salary<0)
			return "工资输入错误";
		return "success";
	}
}
