package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
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

		// 生成id
		String maker = this.getMakePersonId();
		String transitId = this.getCurrentTransitID(maker);
		String receiptId = this.creatReceiptID(transitId);

		// 生成po
		TransitArriveSheetPO po = new TransitArriveSheetPO();
		ArriveInformation infoCopy = new ArriveInformation(info);
		po.setTransitArriveInformation(infoCopy);;
		po.setId(receiptId);
		po.setMakePerson(maker);
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.POSITION_RECEIVE_RECEIPT);

		// 提交
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		return new ResultMessage(Result.SUCCESS);
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
		ArriveInformation info=((TransitArriveSheetVO)vo).getTransitArriveInformation();
		List<ArriveState> states=info.getState();
		
		BarIdList idService=new BarIdList();
		List<String> barIds=idService.getBarIds(info.getTransitSheetId());
		
		//获得本中转中心名称
		Transit transit=new Transit();
		String transitId=vo.getId().split(IdType.POSITION_RECEIVE_RECEIPT.getIdStr())[0];
		String transitName=transit.observeTransit(transitId).getName(); 
		
		//更新物流信息
		Deliver deliver=new Deliver();
		for(int i=0;i<barIds.size();i++){
			String trail=transitName+"已收入。";
			trail=trail+states.get(i)+" "+info.getTime();
			deliver.updateDeliverInfo(barIds.get(i), trail, DeliveryState.TRANSIT_IN);
		}
		
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		TransitArriveSheetPO receipt=(TransitArriveSheetPO)po;
		ArriveInformation info=new ArriveInformation(receipt.getTransitArriveInformation());
		TransitArriveSheetVO vo=new TransitArriveSheetVO();
		vo.setTransitArriveInformation(info);;
		vo.copy(po);
		return vo;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		TransitArriveSheetVO receipt=(TransitArriveSheetVO)vo;
		ArriveInformation info=new ArriveInformation(receipt.getTransitArriveInformation());
		TransitArriveSheetPO po=new TransitArriveSheetPO();
		po.setTransitArriveInformation(info);;
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
		if (info.getState() == null)
			return new ResultMessage(Result.FAIL, "中转单不存在");
		return new ResultMessage(Result.SUCCESS);
	}

}
