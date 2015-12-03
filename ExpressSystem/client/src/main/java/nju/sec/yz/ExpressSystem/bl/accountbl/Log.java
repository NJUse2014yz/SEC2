package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.po.LogPO;
import nju.sec.yz.ExpressSystem.vo.LogVO;

/**
 * 日志的领域模型对象
 * @author 周聪
 *
 */
public class Log {
	private LogDataService data;
	public Log(){
		try {
			data=DatafactoryProxy.getLogDataService();
		} catch (RemoteException e) {
			//TODO 远程异常
			e.printStackTrace();
		}
	}
	public ArrayList<LogVO> getAll() {
		ArrayList<LogPO> listPO = null;
		ArrayList<LogVO> listVO = new ArrayList<LogVO>();
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
			LogVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	public ArrayList<LogVO> getByTime(String time) {
		List<LogPO> listPO = null;
		ArrayList<LogVO> listVO = new ArrayList<LogVO>();
		//获取数据库中的userpo列表
		try {
			listPO=data.find(time);
			if(listPO==null)
				return null;
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			LogVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}
	
	public ResultMessage addLog(LogVO vo){
		ResultMessage message=null;
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success")){
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		try {
			LogPO po=changeVoToPo(vo);
			message=data.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}
	
	private String isValid(LogVO vo) {
		String time=vo.getTime();
		if(!ValidHelper.isValidDate(time))
			return "Wrong Time";
		return "success";
	}
	private LogPO changeVoToPo(LogVO vo) {
		String time=vo.getTime();
		String operation=vo.getOperation();
		String person=vo.getPerson();
		LogPO po=new LogPO(time, operation, person);
		return po;
	}

	private LogVO changePoToVo(LogPO po) {
		String time=po.getTime();
		String operation=po.getOperation();
		String person=po.getPerson();
		LogVO vo=new LogVO(time, operation, person);
		return vo;
	}

	
}
