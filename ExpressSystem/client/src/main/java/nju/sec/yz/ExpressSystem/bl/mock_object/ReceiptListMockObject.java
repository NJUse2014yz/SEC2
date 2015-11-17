package nju.sec.yz.ExpressSystem.bl.mock_object;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.po.DeliverySheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class ReceiptListMockObject implements ReceiptSaveService{
	
	
	public ArrayList<ReceiptVO> getAll() {
		System.out.println("getting all receipt...");
		return null;
	}
	
	public ReceiptVO getSingle(String id) {
		System.out.println("getting a receipt from data service...");
		DeliverReceiptMockObject receipt=new DeliverReceiptMockObject();
		DeliverySheetPO po=new DeliverySheetPO();
		receipt.show(po);
		System.out.println("showing");
		return null;
	}

	
	public ResultMessage approve(ReceiptVO vo) {
		
		DeliverReceiptMockObject receipt=new DeliverReceiptMockObject();
		receipt.approve(vo);
		delete(vo.getId());
		return null;
	}


	public ArrayList<ReceiptVO> getByType(ReceiptType type) {
		System.out.println("getting some  deliver_receipt...");
		return null;
	}

	
	public ResultMessage modify(ReceiptVO vo) {
		System.out.println("modifing a receipt");
		DeliverReceiptMockObject receipt=new DeliverReceiptMockObject();
		ReceiptPO po=receipt.modify(vo);
		System.out.println("receipt "+po.getId()+" modify success");
		return null;
	}


	private ResultMessage delete(String id){
		System.out.println("deleting a receipt...");
		return null;
	}
	
	@Override
	/**
	 * 制定单据完成后通过此方法保存
	 */
	public ResultMessage saveReceipt(ReceiptPO po) {
		System.out.println("saving a receipt...");
		return null;
	}
	
}
