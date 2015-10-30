package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AgencyVO;

/**
 * @author xiaosaisai
 * AgencyBlService对应的驱动
 */
public class AgencyBlDriver {

	public  void drive(AgencyBlService agencyBlService){
		//1
		ResultMessage message1=agencyBlService.addAgency(new AgencyVO("南京", "12", "顺丰快递"));
		if(message1==ResultMessage.SUCCESS)
			System.out.println("添加机构成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//2
		ResultMessage message2=agencyBlService.deleteAgency("12");
		if(message2==ResultMessage.SUCCESS)
			System.out.println("删除机构成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//3
		ResultMessage message3=agencyBlService.modifyAgency(new AgencyVO("南京", "12", "顺丰快递"));
		if(message3==ResultMessage.SUCCESS)
			System.out.println("修改机构成功");
		else 
			System.out.println("不好意思，操作失败哟");
		//4
		AgencyVO avo=agencyBlService.observeAgency("12");
		System.out.println(avo.getLocation()+" "+avo.getId()+" "+avo.getName());
		//5
		ArrayList<AgencyVO> list=agencyBlService.observeAllAgency();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getLocation()+" "+list.get(i).getId()+" "+list.get(i).getName());
		}
	}
//	public static void main(String[] args) {
//		AgencyBlService service=new AgencyBlStub();
//		AgencyBlDriver driver=new AgencyBlDriver();
//		driver.drive(service);
//	}
}
