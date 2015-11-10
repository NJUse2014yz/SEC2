package nju.sec.yz.ExpressSystem.bl.object_driver;

import nju.sec.yz.ExpressSystem.bl.mock_object.ReceiptListMockObject;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class ReceiptDriver {

	public void drive(ReceiptListMockObject list) {
		ReceiptVO receiptVO = new SendSheetVO();
		receiptVO.setType(ReceiptType.DELIVER_RECEIPT);
		
		//
		list.approve(receiptVO);
		
		//
		list.getAll();
		
		//
		list.getSingle(null);
		
		//
		list.modify(receiptVO);
	}
	
	public static void main(String[] args) {
		new ReceiptDriver().drive(new ReceiptListMockObject());
	}
	
	
}
