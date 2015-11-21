package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * 汽车信息的领域模型对象
 * @author 周聪
 *
 */
public class Car {
	
	private CarDataService carData;
	
	public Car() {
		try {
			carData=DatafactoryProxy.getCarDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<CarVO> getAll() {
		ArrayList<CarPO> listPO = null;
		ArrayList<CarVO> listVO = new ArrayList<CarVO>();
		//获取数据库中的userpo列表
		try {
			listPO=carData.findAll();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			CarVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	private CarVO changePoToVo(CarPO carPO) {
		// TODO 自动生成的方法存根
		return null;
	}

	public CarVO getSingle(String id) {
		CarVO vo=null;
		try {
			CarPO	po=carData.find(id);
			vo=changePoToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}

	
	public ResultMessage add(CarVO vo) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success")){
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL,validresult);
		}
		//创建PO并保存
		try {
			CarPO po=changeVoToPo(vo);
			message=carData.insert(po);
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
			result=carData.delete(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return result;
	}

	
	public ResultMessage modify(CarVO vo) {
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(vo);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		CarPO po=changeVoToPo(vo);
		try {
			message=carData.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	private String isValid(CarVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	private CarPO changeVoToPo(CarVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}
}
