package nju.sec.yz.ExpressSystem.bl.object_driver;

import nju.sec.yz.ExpressSystem.bl.mock_object.DeliverReceiptMockObject;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class DeliverDriver {

	public void drive(DeliverReceiptMockObject receipt){
		receipt.make(new ReceiptVO());
	}
	
	public static void main(String[] args) {
		new DeliverDriver().drive(new DeliverReceiptMockObject());
	}
	
}
