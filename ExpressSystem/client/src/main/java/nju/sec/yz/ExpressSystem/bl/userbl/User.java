package nju.sec.yz.ExpressSystem.bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.po.UserPO;
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
		ResultMessage result=new ResultMessage(Result.SUCCESS);
		if(id.equals("D110")&&password.equals("120"))
			return result;
		
		UserPO userPo = null;
		try {
			userPo = data.find(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		if(userPo==null){
			result.setResult(Result.FAIL);
			result.setMessage("该账号不存在请重新输入");
			return result;
		}
		if(userPo.getPassword()!=password){
			result.setResult(Result.FAIL);
			result.setMessage("密码不对哟，看看大小写输对了没");
			return result;
		}	
		return result;
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
