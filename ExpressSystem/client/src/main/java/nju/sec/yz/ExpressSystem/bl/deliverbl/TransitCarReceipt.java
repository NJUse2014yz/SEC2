package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransitCarInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitCarSheetPO;
import nju.sec.yz.ExpressSystem.po.TransitCarSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

public class TransitCarReceipt implements ReceiptService {

	@Override
	public ResultMessage make(ReceiptVO vo) {
		TransitSheetVO receipt = (TransitSheetVO) vo;
		TransitCarInformation info = (TransitCarInformation) receipt.getTransitInformation();
		List<String> barIds = info.getBarIds();
		TransitReceipt helper = new TransitReceipt();

		// 验证
		ResultMessage validResult = isValid(receipt);
		if (validResult.getResult() == Result.FAIL)
			return validResult;

		// 生成各种id
		String transitID = helper.getCurrentTransitID();
		String transportID = this.createTransportID(transitID);
		String receiptId = helper.creatReceiptID(transportID);
		info.setCarTransitId(transportID);

		// 计算运费
		double distance = helper.distance(info.getDeparture(), info.getDestination());
		double fare = this.cost(barIds.size(), distance);
		info.setFare(fare);

		// 生成po
		TransitCarSheetPO po = new TransitCarSheetPO();
		TransitCarInformation infoCopy = new TransitCarInformation(info);
		po.setTransitInformation(infoCopy);
		po.setId(receiptId);
		po.setMakePerson(helper.getMakePersonId());
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.TRANSIT_CAR_RECEIPT);

		// 提交
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		// 保存条形码号供到达单使用
		helper.saveBarIds(barIds, receiptId);

		return new ResultMessage(Result.SUCCESS, fare + " " + transportID);
	}

	/**
	 * 生成汽运编号
	 */
	private String createTransportID(String transitId) {
		ReceiptID id = new ReceiptID();
		String transportID = id.getID(transitId, IdType.TRANSIT_CAR_TRANSPORT);
		return transportID;
	}

	/**
	 * 假设100个包裹为1吨
	 */
	private double cost(int num, double distance) {
		double weight = num / 100.0;

		PriceService priceService = new Price();
		double price = priceService.getCarPrice();
		double cost = weight * distance * price;

		return cost;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		TransitSheetVO receipt=(TransitSheetVO)vo;
		TransitCarInformation info=(TransitCarInformation)receipt.getTransitInformation();
		TransitCarInformation infoCopy=new TransitCarInformation(info);
		TransitCarSheetPO po=new TransitCarSheetPO();
		po.setTransitInformation(infoCopy);
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		TransitSheetVO receipt = (TransitSheetVO) vo;
		TransitReceipt helper = new TransitReceipt();
		ResultMessage validResult = helper.isValid(receipt.getTransitInformation());
		if (validResult.getResult() == Result.FAIL)
			return validResult;
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		TransitCarSheetPO receipt=(TransitCarSheetPO)po;
		TransitCarInformation info=new TransitCarInformation(receipt.getTransitInformation());
		
		TransitSheetVO vo=new TransitSheetVO();
		vo.setTransportType(TransportType.CAR);
		vo.setTransitInformation(info);
		vo.copy(po);
		
		return vo;
	}

}
