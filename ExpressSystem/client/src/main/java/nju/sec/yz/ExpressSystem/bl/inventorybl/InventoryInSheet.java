package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.presentation.controlerui.InventoryControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

/**
 * 入库单的领域模型对象
 * @author 周聪
 *
 */
public class InventoryInSheet implements ReceiptService {
	private InventoryDataService data;
	
	public InventoryInSheet(){
		try {
			data=DatafactoryProxy.getInventoryDataService();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//通过入库单的审批
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		Inventory inventory=new Inventory();
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		InventoryInSheetPO inPO = new InventoryInSheetPO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		ResultMessage resultMessage=inventory.updateInReceipt(inPO);
		System.out.println("Approving...");
		return resultMessage;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		
		//验证information
		ResultMessage message=isValid(vo);
		if(message.getResult()==Result.FAIL)
			return message;
		
		//创建PO交给receiptList
		InventoryInSheetPO inPO =(InventoryInSheetPO) convertToPO(vo);
		inPO.setId(createID());
		inPO.setType(ReceiptType.INVENTORY_IN);
		inPO.setMakePerson(this.getInVentorID());
		inPO.setMakeTime(TimeTool.getDate());
		
		ReceiptList receiptList = new ReceiptList();
		receiptList.saveReceipt(inPO);
		
		return new ResultMessage(Result.SUCCESS);
	}

	
	private String getInVentorID() {
		// TODO 自动生成的方法存根
		return null;
	}

	/**
	 * 生成入库单ID
	 * @return
	 */
	private String createID() {
		// TODO 自动生成的方法存根
		return null;
	}

	public ResultMessage isValid(InventoryInInformation ii){
		//假定柜子刚好99个
		if(ii.getBlock()==0){
			return new ResultMessage(Result.FAIL, "hhh");
			//增加ResultMessage，返回fail的具体原因
		}
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		InventoryInSheetPO inPO = new InventoryInSheetPO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		return inPO;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
