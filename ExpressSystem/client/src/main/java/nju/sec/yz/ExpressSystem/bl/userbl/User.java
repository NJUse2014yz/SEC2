package nju.sec.yz.ExpressSystem.bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.vo.UserVO;

/**
 * 负责管理用户
 * @author 周聪
 *
 */
public class User {
private UserDataService data;
	
	public User(){
		try {
			data=DatafactoryProxy.getUserDataService();
		} catch (RemoteException e) {
			//TODO 远程异常
			e.printStackTrace();
		}
	}
	public ResultMessage login(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<UserVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public UserVO getSingle(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage modify(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
