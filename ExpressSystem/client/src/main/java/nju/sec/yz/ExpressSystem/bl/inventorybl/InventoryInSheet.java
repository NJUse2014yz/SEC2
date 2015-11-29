package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 入库单的领域模型对象
 * @author 周聪
 * @changer sai
 */
public class InventoryInSheet implements ReceiptService {
	private InventoryInDataService data;
	
	public InventoryInSheet() {
		try {
			data=DatafactoryProxy.getInventoryInDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通过入库单的审批
	 */
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		InventoryInSheetPO inPO = (InventoryInSheetPO)convertToPO(vo);
		ResultMessage resultMessage=updateInReceipt(inPO);
		System.out.println("Approving...");
		return resultMessage;
	}
	
	/**
	 * 生成入库单
	 */
	@Override
	public ResultMessage make(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		//验证information
		ResultMessage message=isValid(inSheet);
		if(message.getResult()==Result.FAIL)
			return message;
		
		//创建PO交给receiptList
		InventoryInSheetPO inPO =(InventoryInSheetPO) convertToPO(vo);
		inPO.setId(createID());
		inPO.setType(ReceiptType.INVENTORY_IN);
		inPO.setMakePerson(this.getUserID());
		inPO.setMakeTime(TimeTool.getDate());
		
		ReceiptList receiptList = new ReceiptList();
		receiptList.saveReceipt(inPO);
		
		return new ResultMessage(Result.SUCCESS);
	}
	
	/**
	 * 生成入库单ID
	 */
	private String createID() {
		String inventorId=this.getTransitID();
		ReceiptID idMaker=new ReceiptID();
		String id=idMaker.getID(inventorId, IdType.INVENTORY_IN);
		return id;
	}
	/**
	 * 获得中转中心ID
	 */
	private String getTransitID() {
		String userid=getUserID();
		String[] strs=userid.split("A");
		return strs[0];
	}
	private String getUserID() {
		UserInfo user=new User();
		String userid=user.getCurrentID();
		return userid;
	}
	
	public ResultMessage updateInReceipt(InventoryInSheetPO inPO) {
		ResultMessage message=null;
		InventoryListPO inventoryPO=new InventoryListPO( );
		
		try {
			message=data.insert(inPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	@Override
	public ReceiptVO show(ReceiptPO po) {
		InventoryInSheetPO inSheet=(InventoryInSheetPO)po;
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		InventoryInSheetVO inVO = new InventoryInSheetVO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		
		inVO.setId(inSheet.getId());
		inVO.setType(ReceiptType.INVENTORY_IN);
		inVO.setMakePerson(inSheet.getMakePerson());
		inVO.setMakeTime(inSheet.getMakeTime());
		return inVO;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		InventoryInSheetVO inSheet=(InventoryInSheetVO)vo;
		InventoryInInformation ii=inSheet.getInventoryInInformation();
		InventoryInSheetPO inPO = new InventoryInSheetPO(new InventoryInInformation(
				ii.getTime(),ii.getDestination(), ii.getBlock(), ii.getRow(), 
				ii.getShelf(), ii.getPositon()),inSheet.getBarId());
		
		inPO.setId(inSheet.getId());
		inPO.setType(ReceiptType.INVENTORY_IN);
		inPO.setMakePerson(inSheet.getMakePerson());
		inPO.setMakeTime(inSheet.getMakeTime());
		return inPO;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		InventoryInSheetVO ivo=(InventoryInSheetVO)vo;
		InventoryInInformation information=ivo.getInventoryInInformation();
		
		String time=information.getTime();
		//String destination=information.getDestination();不判断
		int block=information.getBlock();
		int row=information.getRow();
		int shelf=information.getShelf();
		int positon=information.getPositon();
		String id=ivo.getBarId();
		
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		if(!ValidHelper.isBeforeDate(time))
			message.setMessage("入库日期不符合规范啊");
		if(!ValidHelper.isBarId(id))
			message.setMessage("订单条形码不对啊");
		if(!ValidHelper.isBlock(block))
			message.setMessage("区号不对哟");
		if(!ValidHelper.isValidInt(row))
			message.setMessage("行号不对哟");
		if(!ValidHelper.isValidInt(shelf))
			message.setMessage("架号不对哟");
		if(!ValidHelper.isValidInt(positon))
			message.setMessage("位号不对哟");
		return message;
	}
}
