package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.UserBlStub2;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService2;
import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

/**
 * @author xiaosaisai
 * UserBlService对应的驱动
 * rename
 */
public class UserBlDriver {
	public  void drive(UserBlService2 userBlService){
		//1
		ResultMessage message1=userBlService.add(new UserVO("s001","刘强东","jingdong",Power.MANAGER));
		if(message1==ResultMessage.SUCCESS)
			System.out.println("添加用户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//2
		ResultMessage message2=userBlService.del("s001");
		if(message2==ResultMessage.SUCCESS)
			System.out.println("删除用户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//3
		ResultMessage message3=userBlService.modify(new UserVO("s001","刘强东","jingdong",Power.MANAGER));
		if(message3==ResultMessage.SUCCESS)
			System.out.println("修改用户成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//4
		UserVO uvo=userBlService.getSingle("s001");
		System.out.println(uvo.getId()+" "+uvo.getName()+" "+uvo.getPassword()+" "+uvo.getPassword());
		//5
		ArrayList<UserVO> list=userBlService.getAll();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getId()+" "+list.get(i).getName()+
					" "+list.get(i).getPassword()+" "+list.get(i).getPassword());
		//6
		ResultMessage message4=userBlService.login("s001", "jingdong");
		if(message4==ResultMessage.SUCCESS)
			System.out.println("登陆成功");
		else 
			System.out.println("不好意思，登陆失败哟");
		}
	}
//	public static void main(String[] args) {
//		UserBlService service=new UserBlStub();
//		UserBlDriver driver=new UserBlDriver();
//		driver.drive(service);
//	}
}
