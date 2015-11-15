package nju.sec.yz.ExpressSystem.bl.mock_object;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.DeliverySheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class DeliverReceiptMockObject implements ReceiptService{

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		System.out.println("handling a approved deliver receipt");
		System.out.println("receipt id:"+vo.getId());
		DeliverMockObject deliver=new DeliverMockObject();
		SendSheetPO po=new SendSheetPO();
		po.setId(vo.getId());
		deliver.updateDeliverReceipt(po);
		return null;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		System.out.println("making a deliver receipt...");
		return null;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		System.out.println("modifying a deliver receipt...");
		SendSheetPO po=new SendSheetPO();
		po.setId(vo.getId());
		return po;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		System.out.println("showing a deliver receipt...");
		System.out.println("receipt id:"+po.getId());
		SendSheetVO vo=new SendSheetVO();
		vo.setId(po.getId());
		return vo;
	}

}
