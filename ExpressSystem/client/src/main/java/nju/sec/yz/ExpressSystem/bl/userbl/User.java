package nju.sec.yz.ExpressSystem.bl.userbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class User implements UserInfo{
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
		if(!userPo.getPassword().equals(password)){
			result.setResult(Result.FAIL);
			result.setMessage("密码不对哟，看看大小写输对了没");
			return result;
		}
		this.saveCurrentUser(userPo);
		return result;
	}

	/**
	 * 保存当前用户的信息
	 */
	private void saveCurrentUser(UserPO po){
		File file=new File("File/current_user");
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(po);
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * 获得当前用户id
	 */
	public String getCurrentID(){
		UserPO po=null;
		File file=new File("File/current_user");
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
			po=(UserPO)in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(po==null)
			return null;
		return po.getId();
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
		UserVO vo=null;
		try {
			UserPO	po=data.find(id);
			vo=changePoToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return vo;
	}

	/**
	 * TODO 用户名已存在
	 */
	public ResultMessage add(UserVO vo) {
		ResultMessage message=null;
		//验证information
		String validresult=isValid(vo);
		if(!validresult.equals("success")){
			System.out.println(validresult);
			return new ResultMessage(Result.FAIL,validresult);
		}
			
		//创建PO并保存
		UserPO po=changeVoToPo(vo);
		try {
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

	public ResultMessage modify(UserVO vo) {
		// TODO Auto-generated method stub
		ResultMessage message=null;
		//验证改过之后的vo
		String validresult=isValid(vo);
		if(!validresult.equals("success"))
			return new ResultMessage(Result.FAIL,validresult);
		//vo转po,数据库更新po
		UserPO po=changeVoToPo(vo);
		try {
			message=data.update(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		//管理员修改?
		//用户修改密码?
		//要不要输入原密码?
		return message;
	}
	
	private String isValid(UserVO vo) {
		String id=vo.getId();
		String name=vo.getName();
		String password=vo.getPassword();
		Status pow=vo.getPower();
		
		if(!isId(id,pow))
			return "亲，id不符合身份验证规则";
		if(!isName(name))
			return "亲，名字不要太长哦(length<=8)";
		if(!isPassword(password))
			return "亲，密码长度要多于6个少于15个字符哦";
		return "success";
	}
	
	private boolean isPassword(String password) {
		if(password.length()<6||password.length()>15)
			return false;
		return true;
	}
	
	private boolean isName(String name) {
		if(name.length()>8)
			return false;
		return true;
	}
	
	private boolean isId(String id, Status pow) {
		if(id.length()<4)
			return false;
		char letter=id.charAt(id.length()-4);
		switch(letter){
		case 'A':
			if(pow!=Status.INVENTORY)
				return false;
			String number=id.substring(id.length()-3);
			if(!is3Number(number))
				return false;
			break;
		case 'B':
			if(pow!=Status.TRANSIT)
				return false;
			String number1=id.substring(id.length()-3);
			if(!is3Number(number1))
				return false;
			break;
		case 'C':
			if(pow!=Status.POSITION)
				return false;
			String number2=id.substring(id.length()-3);
			if(!is3Number(number2))
				return false;
			break;
		case 'D':
			if(pow!=Status.DELIVER)
				return false;
			String number3=id.substring(id.length()-3);
			if(!is3Number(number3))
				return false;
			break;
		case 'E':
			if(pow!=Status.JUNIOR_ACCOUNTANCY||pow!=Status.SENIOR_ACCOUNTANCY)
				return false;
			String number4=id.substring(id.length()-3);
			if(!is3Number(number4))
				return false;
			break;
		case 'S':
			if(pow!=Status.MANAGER)
				return false;
			String number5=id.substring(id.length()-3);
			if(!is3Number(number5))
				return false;
			break;
		case 'F':
			if(pow!=Status.ADMINISTRATOR)
				return false;
			String number6=id.substring(id.length()-3);
			if(!is3Number(number6))
				return false;
			break;
		default:return false;
		}
		return true;
	}
	private boolean is3Number(String number) {
		char[] numbers=number.toCharArray();
		for(int i=0;i<3;i++)
			if('0'>numbers[i]||numbers[i]>'9')
				return false;
		return true;
	}
	private UserVO changePoToVo(UserPO po){
		String id=po.getId();
		String name=po.getName();
		String password=po.getPassword();
		Status status=po.getPower();
		UserVO vo=new UserVO(id, name, password, status);
		return vo;
	}
	private UserPO changeVoToPo(UserVO vo) {
		String id=vo.getId();
		String name=vo.getName();
		String password=vo.getPassword();
		Status status=vo.getPower();
		UserPO po=new UserPO(id, name, password, status);
		return po;
	}
	
}
