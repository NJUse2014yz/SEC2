package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.DriverBlStub;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

/**
 * 
 * @author 周聪
 * 
 *         司机逻辑测试驱动
 *
 */
public class DriverBlDriver {

	public void drive(DriverBlService driverBl) {

		//
		ResultMessage modifyResult = driverBl.modify(null);

		System.out.println("Modify driver Fail");

		//
		ResultMessage result = driverBl.add(
				new DriverVO("025001A020", "许贺", "19680304", "3214631968030400254", "15483794533", Sex.MALE, "6年"));

		System.out.println("Add driver Fail");

		//
		ResultMessage delResult = driverBl.del("hhh");

		System.out.println("Delete driver Fail");

		//
		ArrayList<DriverVO> drivers = driverBl.getAll();
		System.out.println("driver name:" + drivers.get(0).getId());
		System.out.println("driver birthDate:" + drivers.get(0).getBirthDate());

		//
		DriverVO driver = driverBl.getSingle("hhh");
		System.out.println("driver name:" + driver.getId());
		System.out.println("driver birthDate:" + driver.getBirthDate());

	}

	public static void main(String[] args) {
		DriverBlService driverBl_stub = new DriverBlStub();
		new DriverBlDriver().drive(driverBl_stub);
	}

}
