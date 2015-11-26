package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.bl.accountbl.Collection;
import nju.sec.yz.ExpressSystem.bl.accountbl.Payment;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionLoadingReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionReceiveReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.PositionSendReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitFlightReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitLoadingReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitReceipt;
import nju.sec.yz.ExpressSystem.bl.deliverbl.TransitReceiveReceipt;
import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryInSheet;
import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryOutSheet;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
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
		RECEIPT_MAP.put(ReceiptType.TRANSIT_FLIGHT_RECEIPT,TransitFlightReceipt.class);
		RECEIPT_MAP.put(ReceiptType.TRANSIT_RECEIVE_RECEIPT,TransitReceiveReceipt.class);
	}
	
	
	public ReceiptList(){
		try {
			receiptData=DatafactoryProxy.getReceiptDataService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReceiptVO> getAll() {
		ArrayList<ReceiptVO> receiptVOs=new ArrayList<>();
		try {
			List<ReceiptPO> receiptPOs=receiptData.findAll();
			ReceiptVO vo=null;
			for(ReceiptPO po:receiptPOs){
				vo=this.show(po);
				receiptVOs.add(vo);
			}
		} catch (RemoteException e) {
			//
			e.printStackTrace();
		}
		return receiptVOs;
	}
	
	public ArrayList<ReceiptVO> getByType(ReceiptType type) {
		ArrayList<ReceiptVO> receiptVOs=new ArrayList<>();
		try {
			List<ReceiptPO> receiptPOs=receiptData.findAll();
			ReceiptVO vo=null;
			for(ReceiptPO po:receiptPOs){
				if(po.getType()==type){
					vo=this.show(po);
					receiptVOs.add(vo);
				}
			}
		} catch (RemoteException e) {
			//
			e.printStackTrace();
		}
		return receiptVOs;
	}
	
	public ReceiptVO getSingle(String id) {
		ReceiptVO vo=null;
		try {
			ReceiptPO po=receiptData.find(id);
			vo=this.show(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	private ReceiptVO show(ReceiptPO po){
		ReceiptVO vo=null;
		try {
			ReceiptService receipt=RECEIPT_MAP.get(po.getType()).newInstance();
			vo=receipt.show(po);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public ResultMessage approve(ReceiptVO vo) {
		ResultMessage message=null;
		try {
			//单据信息更新交给相应receipt处理
			ReceiptService receipt=RECEIPT_MAP.get(vo.getType()).newInstance();
			message=receipt.approve(vo);
			
			//单据删除
			this.delete(vo.getId());
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}



	/**
	 * 修改单据
	 */
	public ResultMessage modify(ReceiptVO vo) {
		ResultMessage message=null;
		try {
			ReceiptService receipt=RECEIPT_MAP.get(vo.getType()).newInstance();
			//TODO 修改信息有误
			
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	
	private ResultMessage update(ReceiptPO po){
		ResultMessage message=null;
		try {
			message=receiptData.update(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		return message;
	}

	private ResultMessage delete(String id){
		System.out.println("deleting a receipt...");
		try {
			receiptData.delete(id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return new ResultMessage(Result.FAIL,"系统错误");
		}
		
		return new ResultMessage(Result.SUCCESS);
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
			return new ResultMessage(Result.FAIL, "系统错误");
		}
		return new ResultMessage(Result.SUCCESS);
	}
	
	
}
