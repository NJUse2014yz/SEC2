package nju.sec.yz.ExpressSystem.bl.inventorybl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 出库单的领域模型对象
 * @author 周聪
 *
 */
public class InventoryOutSheet implements ReceiptService{
	
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		Inventory inventory=new Inventory();
		InventoryOutSheetPO outPO = (InventoryOutSheetPO) convertToPO(vo);
		ResultMessage resultMessage=inventory.updateOutReceipt(outPO);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		InventoryOutSheetVO outSheet=(InventoryOutSheetVO)vo;
		//验证information
		ResultMessage message=isValid(outSheet);
		if(message.getResult()==Result.FAIL)
			return message;
		
		//创建PO交给receiptList
		InventoryOutSheetPO outPO =(InventoryOutSheetPO) convertToPO(vo);
		outPO.setId(createID());
		outPO.setType(ReceiptType.INVENTORY_OUT);
		outPO.setMakePerson(this.getInVentorID());
		outPO.setMakeTime(TimeTool.getDate());
		
		ReceiptList receiptList = new ReceiptList();
		receiptList.saveReceipt(outPO);
		
		return new ResultMessage(Result.SUCCESS);
	}

	/**
	 * 生成入库单ID
	 */
	private String createID() {
		String inventorId=this.getInVentorID();
		ReceiptID idMaker=new ReceiptID();
		String id=idMaker.getID(inventorId, IdType.INVENTORY_OUT);
		return id;
	}
	
	/**
	 * 获得填单人ID
	 * @return
	 */
	private String getInVentorID() {
		UserInfo user=new User();
		String id=user.getCurrentID();
		return id;
	}
	
	@Override
	public ReceiptVO show(ReceiptPO po) {
		InventoryOutSheetPO outSheet=(InventoryOutSheetPO)po;
		InventoryOutInformation io=outSheet.getInventoryOutInformation();
		InventoryOutSheetVO outVO = new InventoryOutSheetVO(new InventoryOutInformation(
				io.getTime(), io.getTransitId(),io.getTransportType(), io.getDestination()),
				outSheet.getBarId());
		
		outVO.setId(outSheet.getId());
		outVO.setType(ReceiptType.INVENTORY_OUT);
		outVO.setMakePerson(outSheet.getMakePerson());
		outVO.setMakeTime(outSheet.getMakeTime());
		return outVO;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		InventoryOutSheetVO outSheet=(InventoryOutSheetVO)vo;
		InventoryOutInformation io=outSheet.getInventoryOutInformation();
		InventoryOutSheetPO outPO = new InventoryOutSheetPO(new InventoryOutInformation(
				io.getTime(), io.getTransitId(),io.getTransportType(), io.getDestination()),
				outSheet.getBarId());
		
		outPO.setId(outSheet.getId());
		outPO.setType(ReceiptType.INVENTORY_OUT);
		outPO.setMakePerson(outSheet.getMakePerson());
		outPO.setMakeTime(outSheet.getMakeTime());
		return outPO;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
