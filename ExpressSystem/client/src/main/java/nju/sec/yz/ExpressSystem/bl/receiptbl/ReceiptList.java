package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nju.sec.yz.ExpressSystem.bl.accountbl.Collection;
import nju.sec.yz.ExpressSystem.bl.accountbl.Payment;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionLoadingReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionReceiveReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionSendReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitLoadingReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitReceiveReceipt;
import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryInSheet;
import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryOutSheet;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 表单列表的领域模型对象
 * 负责存放未被审批的单据，并且控制和外部交互
 * @author 周聪
 */
public class ReceiptList implements ReceiptSaveService{
	
	/**
	 * 获取相应的Receipt
	 */
	private static Map<ReceiptType, Class<? extends ReceiptService>> RECEIPT_MAP = new HashMap<>();
	
	private ReceiptDataService receiptData;
	
	static{
		RECEIPT_MAP.put(ReceiptType.DELIVER_RECEIPT, DeliverReceipt.class);
		RECEIPT_MAP.put(ReceiptType.COLLECTION, Collection.class);
		RECEIPT_MAP.put(ReceiptType.INVENTORY_IN, InventoryInSheet.class);
		RECEIPT_MAP.put(ReceiptType.INVENTORY_OUT, InventoryOutSheet.class);
		RECEIPT_MAP.put(ReceiptType.PAYMENT, Payment.class);
		RECEIPT_MAP.put(ReceiptType.POSITION_LOADING_RECEIPT,PositionLoadingReceipt.class);
		RECEIPT_MAP.put(ReceiptType.POSITION_RECEIVE_RECEIPT,PositionReceiveReceipt.class);
		RECEIPT_MAP.put(ReceiptType.POSITION_SEND_RECEIPT,PositionSendReceipt.class);
		RECEIPT_MAP.put(ReceiptType.TRANSIT_LOADING_RECEIPT,TransitLoadingReceipt.class);
		RECEIPT_MAP.put(ReceiptType.TRANSIT_RECEIPT,TransitReceipt.class);
		RECEIPT_MAP.put(ReceiptType.TRANSIT_RECEIVE_RECEIPT,TransitReceiveReceipt.class);
	}
	
	
	
	
	
	public ReceiptList(){
		try {
			receiptData=DatafactoryProxy.getReceiptDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReceiptVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ReceiptVO getSingle(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage approve(ReceiptVO vo) {
		try {
			//单据信息更新交给相应receipt处理
			ReceiptService receipt=RECEIPT_MAP.get(vo.getType()).newInstance();
			receipt.approve(vo);
			
			//单据删除
			
			
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<ReceiptVO> getByType(ReceiptType type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}


	private ResultMessage delete(String id){
		System.out.println("deleting a receipt...");
		
		
		return ResultMessage.SUCCESS;
	}
	
	@Override
	/**
	 * 制定单据完成后通过此方法保存
	 */
	public ResultMessage saveReceipt(ReceiptPO po) {
		try {
			receiptData.insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
		return ResultMessage.SUCCESS;
	}
	
	
}
