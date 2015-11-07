package nju.sec.yz.ExpressSystem.bl.driver;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.stub.ReceiptBlStub;
import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 
 * @author xiaosaisai
 *
 * 单据审批逻辑测试驱动
 *
 */
public class ReceiptBlDriver {

	public void drive(ReceiptBlService receiptBl_stub){
		//
		ResultMessage approveResult=receiptBl_stub.approve(null);
		//
		List<ReceiptVO> receipts= receiptBl_stub.getAll();
		System.out.println("待审批单据："+receipts);
		//
		List<ReceiptVO> receipts2= receiptBl_stub.getByType(null);
		System.out.println("寄件单："+receipts2);
		//
		ReceiptVO receiptVO=receiptBl_stub.getSingle(0);
		System.out.println("单据id:"+receiptVO.getId());
		//
		ResultMessage modifyResult=receiptBl_stub.modify(null);
		System.out.println("修改成功");
		
	}
	
//	public static void main(String[] args) {
//		ReceiptBlService receiptBl_stub=new ReceiptBlStub();
//		new ReceiptBlDriver().drive(receiptBl_stub);
//	}
	
}
