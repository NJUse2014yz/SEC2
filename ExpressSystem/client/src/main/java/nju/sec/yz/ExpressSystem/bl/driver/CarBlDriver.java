package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.stub.CarBlStub;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;

import nju.sec.yz.ExpressSystem.common.ResultMessage;

import nju.sec.yz.ExpressSystem.vo.CarVO;

/**
 * 
 * @author 周聪
 *
 *         汽车逻辑测试驱动
 */
public class CarBlDriver {

	public void drive(CarBlService carBl) {

		//
		ResultMessage modifyResult = carBl.modify(null);

		System.out.println("Modify car Fail");

		//
		ResultMessage result = carBl.add(null);

		System.out.println("Add car Fail");

		//
		ResultMessage delResult = carBl.del("hhh");

		System.out.println("Delete car Fail");

		//
		ArrayList<CarVO> drivers = carBl.getAll();
		System.out.println("car name:" + drivers.get(0).getId());

		//
		CarVO driver = carBl.getSingle("hhh");
		System.out.println("car name:" + driver.getId());

	}
	// public static void main(String[] args) {
	// CarBlService carBl_stub=new CarBlStub();
	// new CarBlDriver().drive(carBl_stub);
	// }

}
