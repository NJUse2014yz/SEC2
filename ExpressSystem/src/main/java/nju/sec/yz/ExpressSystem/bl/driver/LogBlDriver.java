package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.LogBlStub;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.vo.LogVO;

/**
 * @author xiaosaisai
 * LogBlService对应的驱动
 */
public class LogBlDriver {
	public void drive(LogBlService service) {
		//1
		LogVO lvo=service.getSingle(0);
		System.out.println(lvo.getPerson()+" "+lvo.getOperation()+" "+lvo.getTime());
		//2
		ArrayList<LogVO> list1=service.getAll();
		for(int i=0;i<list1.size();i++){
			System.out.println(list1.get(i).getPerson()+" "+list1.get(i).getOperation()+" "+list1.get(i).getTime());
		}
		//3
		ArrayList<LogVO> list2=service.getByTime("20151025", "20151026");
		for(int i=0;i<list2.size();i++){
			System.out.println(list2.get(i).getPerson()+" "+list2.get(i).getOperation()+" "+list2.get(i).getTime());
		}
	}
//	public static void main(String[] args) {
//		LogBlService service=new LogBlStub();
//		LogBlDriver driver=new LogBlDriver();
//		driver.drive(service);
//	}

	
}
