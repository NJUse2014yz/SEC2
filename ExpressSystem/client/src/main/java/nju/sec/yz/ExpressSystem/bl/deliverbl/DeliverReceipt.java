package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.ObjectDeepCopy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
/**
 * 寄件单的领域模型
 * @author 周聪
 */
public class DeliverReceipt implements ReceiptService{
	@Override
	/**
	 * 检验输入信息
	 * 生成PO
	 */
	public ResultMessage make(ReceiptVO vo) {
		SendSheetVO sendReceipt=(SendSheetVO)vo;
		SendInformation information=sendReceipt.getSendInformation();
		
		//验证information
		
		
		//创建PO交给receipt
		SendSheetPO receipt=new SendSheetPO();
		sendReceipt.setId(null);
		sendReceipt.setSendInformation(information);
		ReceiptSaveService receiptList=new ReceiptList();
		receiptList.saveReceipt(receipt);
		
		return ResultMessage.SUCCESS;
	}

	@Override
	/**
	 * 审批完成后更新信息
	 */
	public ResultMessage approve(ReceiptVO vo) {
		SendSheetVO receipt=(SendSheetVO)vo;
		Deliver deliver=new Deliver();
		SendInformation information=receipt.getSendInformation();
		SendSheetPO po=new SendSheetPO();
		//
		SendInformation saveInformation = (SendInformation) ObjectDeepCopy.deepCopy(information);
		po.setSendInformation(saveInformation);
		ResultMessage resultMessage=deliver.updateDeliverReceipt(po);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ReceiptPO modify(ReceiptVO vo) {
		return null;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

}
