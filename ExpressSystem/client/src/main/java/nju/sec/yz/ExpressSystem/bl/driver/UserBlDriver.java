package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.UserBlStub;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

/**
 * @author xiaosaisai UserBlService对应的驱动 rename
 */
public class UserBlDriver {
	public void drive(UserBlService userBlService) {
		// 1
		ResultMessage message1 = userBlService.add(new UserVO("s001", "刘强东", "jingdong", Status.MANAGER));

		System.out.println("不好意思，操作失败哟");
		// 2
		ResultMessage message2 = userBlService.del("s001");

		System.out.println("不好意思，操作失败哟");
		// 3
		ResultMessage message3 = userBlService.modify(new UserVO("s001", "刘强东", "jingdong", Status.MANAGER));

		System.out.println("不好意思，操作失败哟");
		// 4
		UserVO uvo = userBlService.getSingle("s001");
		System.out.println(uvo.getId() + " " + uvo.getName() + " " + uvo.getPassword() + " " + uvo.getPassword());
		// 5
		ArrayList<UserVO> list = userBlService.getAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getPassword() + " "
					+ list.get(i).getPassword());
			// 6
			ResultMessage message4 = userBlService.login("s001", "jingdong");

			System.out.println("不好意思，登陆失败哟");
		}
	}
	// public static void main(String[] args) {
	// UserBlService service=new UserBlStub();
	// UserBlDriver driver=new UserBlDriver();
	// driver.drive(service);
	// }
}
