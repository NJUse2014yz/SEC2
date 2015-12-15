package nju.sec.yz.ExpressSystem.bl.inventorybl;

import java.rmi.RemoteException;

import javax.xml.stream.events.StartDocument;

import nju.sec.yz.ExpressSystem.bl.deliverbl.Deliver;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.InventoryOutInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryOutDataService;
import nju.sec.yz.ExpressSystem.po.InventoryOutSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.InventoryOutSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 出库单的领域模型对象
 * 
 * @author 周聪
 * @changer sai
 */
public class InventoryOutSheet implements ReceiptService {
	protected InventoryOutDataService data;

	public InventoryOutSheet() {
		try {
			data = DatafactoryProxy.getInventoryOutDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	/**
	 * 通过出库单的审批
	 */
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		InventoryOutSheetPO outPO = (InventoryOutSheetPO) convertToPO(vo);
		ResultMessage resultMessage = updateOutReceipt(outPO);
		
		Deliver deliver=new Deliver();
		deliver.updateDeliverInfo(outPO.getBarId(),DeliveryState.INVENTORY_OUT);
		
		System.out.println("Approving...");
		return resultMessage;
	}

	/**
	 * 生成出库单
	 */
	@Override
	public ResultMessage make(ReceiptVO vo) {
		InventoryOutSheetVO outSheet = (InventoryOutSheetVO) vo;
		// 验证information
		ResultMessage message = isValid(outSheet);
		if (message.getResult() == Result.FAIL)
			return message;

		// 创建PO交给receiptList
		InventoryOutSheetPO outPO = (InventoryOutSheetPO) convertToPO(vo);
		outPO.setId(createID());
		outPO.setType(ReceiptType.INVENTORY_OUT);
		outPO.setMakePerson(this.getUserID());
		outPO.setMakeTime(TimeTool.getDate());

		ReceiptList receiptList = new ReceiptList();
		receiptList.saveReceipt(outPO);

		// 更新物流信息
		Deliver deliver = new Deliver();
		deliver.submit(outSheet.getBarId());

		return new ResultMessage(Result.SUCCESS);
	}

	/**
	 * 生成入库单ID
	 */
	private String createID() {
		String inventorId = this.getTransitID();
		ReceiptID idMaker = new ReceiptID();
		String id = idMaker.getID(inventorId, IdType.INVENTORY_OUT);
		return id;
	}

	/**
	 * 获得中转中心ID
	 * 
	 * @return
	 */
	private String getTransitID() {
		String userid = getUserID();
		String[] strs = userid.split("A");
		return strs[0];
	}

	/**
	 * 获得用户ID
	 */
	private String getUserID() {
		UserInfo user = new User();
		String userid = user.getCurrentID();
		return userid;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		InventoryOutSheetPO outSheet = (InventoryOutSheetPO) po;
		InventoryOutInformation io = outSheet.getInventoryOutInformation();
		InventoryOutSheetVO outVO = new InventoryOutSheetVO(new InventoryOutInformation(io.getTime(), io.getTransitId(),
				io.getTransportType(), io.getDestination()), outSheet.getBarId());

		outVO.setId(outSheet.getId());
		outVO.setType(ReceiptType.INVENTORY_OUT);
		outVO.setMakePerson(outSheet.getMakePerson());
		outVO.setMakeTime(outSheet.getMakeTime());
		return outVO;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		InventoryOutSheetVO outSheet = (InventoryOutSheetVO) vo;
		InventoryOutInformation io = outSheet.getInventoryOutInformation();
		InventoryOutSheetPO outPO = new InventoryOutSheetPO(new InventoryOutInformation(io.getTime(), io.getTransitId(),
				io.getTransportType(), io.getDestination()), outSheet.getBarId());

		outPO.setId(outSheet.getId());
		outPO.setType(ReceiptType.INVENTORY_OUT);
		outPO.setMakePerson(outSheet.getMakePerson());
		outPO.setMakeTime(outSheet.getMakeTime());
		return outPO;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		InventoryOutSheetVO ovo = (InventoryOutSheetVO) vo;
		InventoryOutInformation information = ovo.getInventoryOutInformation();

		String id = ovo.getBarId();
		String time = information.getTime();
		// destination和transportType 不用判断
		String transitId = information.getTransitId();

		ResultMessage message = new ResultMessage(Result.FAIL);

		if (!ValidHelper.isBarId(id))
			message.setMessage("订单条形码号不对哦");
		// 判断系统中是否存在该条形码号的物流信息
		Deliver deliver = new Deliver();
		if (deliver.checkDeliver(id) == null) {
			return new ResultMessage(Result.FAIL, "系统中还没有订单" + id + "的信息哦");
		}
		
		if(!isRightTrail(id))
			return new ResultMessage(Result.FAIL,"订单号是不是填错了~");
		
		if (!ValidHelper.isBeforeDate(time))
			return new ResultMessage(Result.FAIL,"出库时间不对哦");
		if (!ValidHelper.isTransitID(transitId))
			return new ResultMessage(Result.FAIL,"中转中心编号不对哦");
		return new ResultMessage(Result.SUCCESS);
	}
	
	/**
	 * 物流轨迹是否正确
	 */
	private boolean isRightTrail(String barId){
		Deliver deliver=new Deliver();
		DeliverStateVO vo=deliver.getDeliverState(barId);
		if(!this.getTransitID().equals(vo.nextAgency))
			return false;
		if(vo.state!=DeliveryState.INVENTORY_IN)
			return false;
		return true;
	}

	public ResultMessage updateOutReceipt(InventoryOutSheetPO outPO) {
		ResultMessage message = null;

		// 删除库存
		Inventory inventory = new Inventory();
		inventory.updateOut(getTransitID(), outPO.getBarId());

		try {
			message = data.insert(outPO);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		InventoryOutSheetVO ovo = (InventoryOutSheetVO) vo;
		String message = "条形码号" + ovo.getBarId() + StringTool.nextLine();
		return message;
	}
}
