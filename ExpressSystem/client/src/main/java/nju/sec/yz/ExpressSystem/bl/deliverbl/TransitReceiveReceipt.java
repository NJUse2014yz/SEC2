package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitArriveSheetPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;

/**
 * 中转中心到达单的领域模型
 * 
 * @author 周聪
 *
 */
public class TransitReceiveReceipt implements ReceiptService {

	@Override
	public ResultMessage make(ReceiptVO vo) {
		TransitArriveSheetVO receipt = (TransitArriveSheetVO) vo;
		ArriveInformation info = receipt.getTransitArriveInformation();
		// 验证
		ResultMessage validResult = isValid(receipt);
		if (validResult.getResult() == Result.FAIL)
			return validResult;

		BarIdList list = new BarIdList();
		// 验证中转单是否通过审批
		List<String> barIds = list.getBarIds(info.getTransitSheetId()).barIds;
		for (String barId : barIds) {
			if (!isRightTrail(barId))
				return new ResultMessage(Result.FAIL, "出库单还没通过审批~");
		}
		if (list.isArrived(info.getTransitSheetId()))
			return new ResultMessage(Result.FAIL, "这到达单已经填过了哦~");

		// 生成id
		String maker = this.getMakePersonId();
		String transitId = this.getCurrentTransitID(maker);
		String receiptId = this.creatReceiptID(transitId);

		// 生成po
		TransitArriveSheetPO po = new TransitArriveSheetPO();
		ArriveInformation infoCopy = new ArriveInformation(info);
		po.setTransitArriveInformation(infoCopy);
		
		po.setId(receiptId);
		po.setMakePerson(maker);
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.TRANSIT_RECEIVE_RECEIPT);

		// 提交
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		// 更新物流信息

		list.arrive(info.getTransitSheetId());

		return new ResultMessage(Result.SUCCESS);
	}

	private boolean isRightTrail(String barId) {

		Deliver deliver = new Deliver();
		DeliverStateVO vo = deliver.getDeliverState(barId);
		System.out.println("state:"+vo.state);
		if (vo == null||vo.nextAgency==null)// 物流信息不存在
			return false;

		else if (vo.state != DeliveryState.OFFICE_OUT && vo.state != DeliveryState.INVENTORY_OUT)
			return false;
		return true;
	}

	/**
	 * 从user获得当前的中转中心id 中转中心业务员编号规则：中转中心编号+B+000三位数字
	 */
	public String getCurrentTransitID(String makerId) {
		String transitID = makerId.split("B")[0];
		return transitID;
	}

	public String getMakePersonId() {
		User user = new User();
		String id = user.getCurrentID();
		return id;
	}

	/**
	 * 生成receiptId
	 */
	public String creatReceiptID(String transitID) {
		ReceiptID id = new ReceiptID();
		String receiptID = id.getID(transitID, IdType.TRANSIT_RECEIVE_RECEIPT);
		return receiptID;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		ArriveInformation info = ((TransitArriveSheetVO) vo).getTransitArriveInformation();
		List<ArriveState> states = info.getState();

		BarIdList idService = new BarIdList();
		List<String> barIds = idService.getBarIds(info.getTransitSheetId()).barIds;

		// 获得本中转中心名称
		Transit transit = new Transit();
		String transitId = vo.getId().split(IdType.TRANSIT_RECEIVE_RECEIPT.getIdStr())[0].split("A")[0];;
		
		String transitName = transit.observeTransit(transitId).getName();

		// 更新物流信息
		Deliver deliver = new Deliver();
		for (int i = 0; i < barIds.size(); i++) {
			String trail = transitName + "已收入。";
			DeliveryState deliveryState=DeliveryState.TRANSIT_IN;
			trail = trail + states.get(i) + " " + info.getTime();
			if(states.get(i)==ArriveState.LOST)
				deliveryState=DeliveryState.LOST;
			if(states.get(i)==ArriveState.BROKEN)
				deliveryState=DeliveryState.BROKEN;
			deliver.updateDeliverInfo(barIds.get(i), trail, deliveryState, transitId);
		}

		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		TransitArriveSheetPO receipt = (TransitArriveSheetPO) po;
		ArriveInformation info = new ArriveInformation(receipt.getTransitArriveInformation());
		TransitArriveSheetVO vo = new TransitArriveSheetVO();
		vo.setTransitArriveInformation(info);
		vo.copy(po);
		return vo;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		TransitArriveSheetVO receipt = (TransitArriveSheetVO) vo;
		ArriveInformation info = new ArriveInformation(receipt.getTransitArriveInformation());
		TransitArriveSheetPO po = new TransitArriveSheetPO();
		po.setTransitArriveInformation(info);
		;
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		TransitArriveSheetVO receipt = (TransitArriveSheetVO) vo;
		ArriveInformation info = receipt.getTransitArriveInformation();
		if (!ValidHelper.isBeforeDate(info.getTime()))
			return new ResultMessage(Result.FAIL, "看看日期是不是输错了~");
		if (info.getState() == null || info.getState().size() == 0)
			return new ResultMessage(Result.FAIL, "中转单不存在");
		BarIdList list = new BarIdList();
		if (list.isArrived(info.getTransitSheetId()))
			return new ResultMessage(Result.FAIL, "这到达单已经填过了哦~");
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		ArriveInformation info = ((TransitArriveSheetVO) vo).getTransitArriveInformation();
		String message = "出发地：" + info.getDeparture() + StringTool.nextLine();
		message = message + "中转单编号：" + info.getTransitSheetId() + StringTool.nextLine();

		return message;
	}
}
