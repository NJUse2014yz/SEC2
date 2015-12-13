package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.ArriveInformation;
import nju.sec.yz.ExpressSystem.common.ArriveState;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.OfficeArriveSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 营业厅到达单的领域模型
 * 
 * @author 周聪
 *
 */
public class PositionReceiveReceipt implements ReceiptService {

	@Override
	public ResultMessage make(ReceiptVO vo) {
		OfficeArriveSheetVO receipt = (OfficeArriveSheetVO) vo;
		ArriveInformation info = receipt.getOfficeArrive();

		// 验证
		ResultMessage validResult = isValid(receipt);
		if (validResult.getResult() == Result.FAIL)
			return validResult;

		// 生成id
		String makerId = this.getMakePersonId();
		String positionId = this.getCurrentPositionID(makerId);
		String receiptId = this.getReceiptId(positionId);

		// 生成po
		OfficeArriveSheetPO po = new OfficeArriveSheetPO();
		ArriveInformation infoCopy = new ArriveInformation(info);
		po.setOfficeArrive(infoCopy);
		po.setId(receiptId);
		po.setMakePerson(makerId);
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.POSITION_RECEIVE_RECEIPT);
		
		//更新物流信息
		BarIdList list=new BarIdList();
		list.arrive(info.getTransitSheetId());
		
		// 提交
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		return new ResultMessage(Result.SUCCESS);
	}

	private String getReceiptId(String positionId) {
		ReceiptID idMaker = new ReceiptID();
		String id = idMaker.getID(positionId, IdType.POSITION_RECEIVE_RECEIPT);
		return id;
	}

	/**
	 * 从user获得当前的营业厅id 营业厅业务员编号规则：营业厅编号+C+000三位数字
	 */
	private String getCurrentPositionID(String positionerID) {
		String positionID = positionerID.split("C")[0];
		return positionID;
	}

	private String getMakePersonId() {
		UserInfo user = new User();
		String id = user.getCurrentID();
		return id;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		OfficeArriveSheetVO receipt = (OfficeArriveSheetVO) vo;
		ArriveInformation info = receipt.getOfficeArrive();
		if (!ValidHelper.isBeforeDate(info.getTime()))
			return new ResultMessage(Result.FAIL, "看看日期是不是输错了~");
		if(info.getState()==null||info.getState().size()==0)
			return new ResultMessage(Result.FAIL,"中转单不存在或目的地不是本营业厅");
		BarIdList list=new BarIdList();
		if(list.isArrived(info.getTransitSheetId()))
			return new ResultMessage(Result.FAIL,"这到达单已经填过了哦~");
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		ArriveInformation info=((OfficeArriveSheetVO)vo).getOfficeArrive();
		List<ArriveState> states=info.getState();
		
		BarIdList idService=new BarIdList();
		List<String> barIds=idService.getBarIds(info.getTransitSheetId()).barIds;
		
		//获得本营业厅名称
		Position position=new Position();
		String positionId=vo.getId().split(IdType.POSITION_RECEIVE_RECEIPT.getIdStr())[0];
		String positionName=position.findPosition(positionId).getName(); 
		
		//更新物流信息
		Deliver deliver=new Deliver();
		for(int i=0;i<barIds.size();i++){
			String trail=positionName+"已收入。";
			trail=trail+states.get(i)+" "+info.getTime();
			deliver.updateDeliverInfo(barIds.get(i), trail, DeliveryState.OFFICE_IN,positionId);
		}
		
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		OfficeArriveSheetPO receipt=(OfficeArriveSheetPO)po;
		ArriveInformation info=new ArriveInformation(receipt.getOfficeArrive());
		OfficeArriveSheetVO vo=new OfficeArriveSheetVO();
		vo.setOfficeArrive(info);
		vo.copy(po);
		return vo;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		OfficeArriveSheetVO receipt=(OfficeArriveSheetVO)vo;
		ArriveInformation info=new ArriveInformation(receipt.getOfficeArrive());
		OfficeArriveSheetPO po=new OfficeArriveSheetPO();
		po.setOfficeArrive(info);
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		ArriveInformation info=((OfficeArriveSheetVO)vo).getOfficeArrive();
		String message="出发地："+info.getDeparture()+StringTool.nextLine();
		message=message+"中转单编号："+info.getTransitSheetId()+StringTool.nextLine();
		
		return message;
	}

}
