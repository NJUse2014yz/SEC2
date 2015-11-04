package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
/**
 * 寄件单的领域模型
 * @author 周聪
 *
 */
public class DeliverReceipt {
	/**
	 * 检验输入信息是否有错
	 * 生成PO
	 */
	public ResultMessage makeDeliverReceipt(SendSheetVO vo) {
		SendInformation information=vo.getSendInformation();
		
		//验证information是否有错
		
		
		//创建PO交给receipt
		SendSheetPO sendReceipt=new SendSheetPO();
		sendReceipt.setId(null);
		sendReceipt.setSendInformation(information);
		ReceiptService receiptList=new ReceiptList();
		receiptList.saveReceipt(sendReceipt);
		
		return null;
	}
}
