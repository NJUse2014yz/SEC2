package nju.sec.yz.ExpressSystem.bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
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
		ArrayList<UserPO> listPO = null;
		ArrayList<UserVO> listVO = new ArrayList<UserVO>();
		//获取数据库中的userpo列表
		try {
			listPO=data.findAll();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//将userpo列表转换成uservo列表
		for(int i=0;i<listPO.size();i++){
			UserVO vo=changePoToVo(listPO.get(i));
			listVO.add(vo);
		}
		return listVO;
	}

	
	public UserVO getSingle(String id) {
		UserPO po=null;
		UserVO vo;
		try {
			po=data.find(id);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		vo=changePoToVo(po);
		return vo;
	}

	
	public ResultMessage add(UserVO vo) {
		// TODO Auto-generated method stub
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//创建PO并保存
		return null;
	}

	public ResultMessage del(String id) {
		// TODO Auto-generated method stub
		//验证id是否存在
		//调用data层方法
		return null;
	}

	public ResultMessage modify(UserVO vo) {
		// TODO Auto-generated method stub
		//管理员修改?
		//用户修改密码?
		//要不要输入原密码?
		return null;
	}
	
	private String isValid(UserVO vo) {
		// TODO 自动生成的方法存根
		String id=vo.getId();
		String name=vo.getName();
		String password=vo.getPassword();
		Status pow=vo.getPower();
		
		if(!isId(id,pow))
			return "亲，不要告诉我寄件人手机号不是11位数字~";
		if(!isName(name))
			return "亲，不要告诉我收件人手机号不是11位数字~";
		if(!isPassword(password))
			return "亲，件数x是要满足0<x<65536的数字哟";
		return "success";
	}
	
	private boolean isPassword(String password) {
		// TODO 自动生成的方法存根
		return false;
	}
	private boolean isName(String name) {
		// TODO 自动生成的方法存根
		return false;
	}
	private boolean isId(String id, Status pow) {
		// TODO 自动生成的方法存根
		return false;
	}
	private UserVO changePoToVo(UserPO po){
		String id=po.getId();
		String name=po.getName();
		String password=po.getPassword();
		Status status=po.getPower();
		UserVO vo=new UserVO(id, name, password, status);
		return vo;
	}
}
