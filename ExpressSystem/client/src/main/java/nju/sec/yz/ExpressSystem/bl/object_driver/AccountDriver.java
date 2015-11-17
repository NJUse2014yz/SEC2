package nju.sec.yz.ExpressSystem.bl.object_driver;

import nju.sec.yz.ExpressSystem.bl.mock_object.PaymentMockObject;
import nju.sec.yz.ExpressSystem.vo.OutVO;

public class AccountDriver {

	public void drive(){
		OutVO vo=new OutVO("1232", 12, "f", "hhh", null, null);
		PaymentMockObject payment=new PaymentMockObject();
		payment.approve(vo);
		
		
	}
	
	public static void main(String[] args) {
		new AccountDriver().drive();
	}
	
}
