package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.StaffBlStub;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Power;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
/**
 * @author xiaosaisai
 * StaffBlService的驱动
 */
public class StaffBlDriver {
	public  void drive(StaffBlService staffBlService){
		//1
		ResultMessage message1=staffBlService.addStaff(new StaffVO("刘强东", "s001", Power.MANAGER,"京东"));
		if(message1==ResultMessage.SUCCESS)
			System.out.println("添加人员成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//2
		ResultMessage message2=staffBlService.deleteStaff("s001");
		if(message2==ResultMessage.SUCCESS)
			System.out.println("删除人员信息成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//3
		ResultMessage message3=staffBlService.modifyStaff(new StaffVO("刘强东", "s001", Power.MANAGER,"京东"));
		if(message3==ResultMessage.SUCCESS)
			System.out.println("修改人员信息成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//4
		StaffVO svo=staffBlService.observeStaff("s001");
		System.out.println(svo.getAgency()+" "+svo.getId()+" "+svo.getName()+" "+svo.getPower());
		//5
		ArrayList<StaffVO> list=staffBlService.observeStaff();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getAgency()+" "+list.get(i).getId()+
					" "+list.get(i).getName()+" "+list.get(i).getPower());
		}
	}
//	public static void main(String[] args) {
//		StaffBlService service=new StaffBlStub();
//		StaffBlDriver driver=new StaffBlDriver();
//		driver.drive(service);
//	}
}