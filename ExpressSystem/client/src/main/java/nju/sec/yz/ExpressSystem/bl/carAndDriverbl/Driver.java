package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

/**
 * 司机信息的领域模型对象
 * @author 周聪
 *
 */
public class Driver {
	
	private DriverDataService data;
	
	public Driver() {
		try {
			data=DatafactoryProxy.getDriverDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<DriverVO> getAll() {
		ArrayList<DriverPO> listPO = null;
		ArrayList<DriverVO> listVO = new ArrayList<DriverVO>();
		//获取数据库中的userpo列表
		try {
			listPO=data.findAll();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			DriverVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	
	private DriverVO changePoToVo(DriverPO driverPO) {
		// TODO 自动生成的方法存根
		return null;
	}

	public DriverVO getSingle(String id) {
		DriverVO vo=null;
		try {
			DriverPO	po=data.find(id);
			vo=changePoToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo; 
	}

	
	public ResultMessage add(DriverVO vo) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success")){
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		try {
			DriverPO po=changeVoToPo(vo);
			message=data.insert(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	public ResultMessage del(String id) {
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

	
	public ResultMessage modify(DriverVO vo) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(vo);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		DriverPO po=changeVoToPo(vo);
		try {
			message=data.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	private DriverPO changeVoToPo(DriverVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	private String isValid(DriverVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
}