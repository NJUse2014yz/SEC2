package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.managerbl.AgencyInfo;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 中转中心装车单的领域模型
 * 
 * @author 周聪
 *
 */
public class TransitLoadingReceipt implements ReceiptService {
	@Override
	public ResultMessage make(ReceiptVO vo) {
		TransitLoadSheetVO receipt = (TransitLoadSheetVO) vo;
		LoadInformation info = receipt.getTransitLoadInformation();
		List<String> barIDs = receipt.getBarIds();

		// 验证
		ResultMessage validResult = isValid(receipt);
		if (validResult.getResult() == Result.FAIL)
			return validResult;

		for (String barID : receipt.getBarIds()) {
			// 判断系统中是否存在该条形码号的物流信息
			if (!isRightTrail(barID)) {
				return new ResultMessage(Result.FAIL, "订单号" + barID + "是不是填错了~");
			}
		}

		// 生成各种id
		String userId = this.getCurrentUserId();
		String transitId = this.getCurrentTransitId(userId);
		String transportId = this.getTransportId(transitId);

		// 计算运费
		double fare = this.cost(barIDs.size());
		info.setFare(fare);

		// 创建po
		info.setTransportId(transportId);
		info.setAgencyId(transitId);
		TransitLoadSheetPO po = new TransitLoadSheetPO();
		LoadInformation information = this.copyInfo(info);
		po.setTransitLoadInformation(information);
		ArrayList<String> barIdsCopy = new ArrayList<>();
		barIdsCopy.addAll(barIDs);
		po.setBarIds(barIdsCopy);
		po.setMakePerson(userId);
		po.setId(transportId);// 汽运编号作为单据id
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.TRANSIT_LOADING_RECEIPT);

		// 提交给receiptlist
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		// 更新物流信息
		Deliver deliver = new Deliver();
		for (String barId : receipt.getBarIds()) {
			deliver.submit(barId);
		}

		

		return new ResultMessage(Result.SUCCESS, fare + "");
	}

	private LoadInformation copyInfo(LoadInformation info) {
		String position = info.getAgencyId();
		String car = info.getCarId();
		String destination = info.getDestinationId();
		double fare = info.getFare();
		String officer = info.getOfficerId();
		String time = info.getTime();
		String transportId = info.getTransportId();
		String driver = info.getDriverId();

		LoadInformation information = new LoadInformation(time, position, transportId, destination, car, officer,
				driver, fare);
		return information;
	}

	/**
	 * 假设100个包裹为1吨
	 */
	private double cost(int num) {
		double weight = num / 100.0;
		double distance = CityConst.DISTANCE_OF_POSITION;
		PriceService priceService = new Price();
		double price = priceService.getCarPrice();
		double cost = weight * distance * price;
		return cost;
	}

	/**
	 * 获得中转中心汽运编号
	 */
	private String getTransportId(String transitId) {
		ReceiptID id = new ReceiptID();
		String transportID = id.getID(transitId, IdType.TRANSIT_CAR_TRANSPORT);
		return transportID;
	}

	/**
	 * 获得当前中转中心业务员id
	 * 
	 * @return
	 */
	private String getCurrentUserId() {
		UserInfo user = new User();
		String id = user.getCurrentID();
		return id;
	}

	/**
	 * 获得中转中心id 中转中心业务员编号规则：中转中心编号+B+000三位数字
	 */
	private String getCurrentTransitId(String userId) {
		String transitId = userId.split("B")[0];
		return transitId;
	}

	public TransitVO getCurrentTransit() {
		String transitId = getCurrentTransitId(getCurrentUserId());

		Transit transit = new Transit();

		return transit.observeTransit(transitId);
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		Deliver deliver = new Deliver();

		ResultMessage validResult = new ResultMessage(Result.FAIL);

		TransitLoadSheetVO receipt = (TransitLoadSheetVO) vo;

		// 验证barid
		List<String> barIDs = receipt.getBarIds();

		// 验证是否有重复
		Set<String> idSet = new HashSet<>(barIDs);
		if (idSet.size() > barIDs.size())
			return new ResultMessage(Result.FAIL, "有条形码号重复了~");

		for (String barID : barIDs) {
			if (!ValidHelper.isBarId(barID)) {
				validResult.setMessage("亲，咱们的订单号是十位数字哟~");
				return validResult;
			}

			// 判断系统中是否存在该条形码号的物流信息
			if (deliver.checkDeliver(barID) == null) {
				validResult.setMessage("系统中还没有订单" + barID + "的信息哦~");
				return validResult;
			}

			if (isRightTrail(barID))
				return new ResultMessage(Result.FAIL, "订单号是不是填错了~");
		}

		// 验证info
		LoadInformation info = receipt.getTransitLoadInformation();
		Car car = new Car();
		if (!ValidHelper.isBeforeDate(info.getTime()))
			validResult.setMessage("看看时间是不是输错了~");
		else if (!car.isId(info.getCarId()))
			validResult.setMessage("看看车辆ID输对了没哦");
		else
			validResult.setResult(Result.SUCCESS);

		return validResult;
	}

	private boolean isRightTrail(String barId) {
		String currentAgency = this.getCurrentTransitId(getCurrentUserId());

		Deliver deliver = new Deliver();
		DeliverStateVO vo = deliver.getDeliverState(barId);

		if (vo == null)// 物流信息不存在
			return false;
		else if (!vo.nextAgency.equals(currentAgency))// 下个机构id不是当前机构
			return false;
		// 先入库再装车再出库
		else if (vo.state != DeliveryState.INVENTORY_IN)
			return false;
		return true;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		List<String> barIds = ((TransitLoadSheetVO) vo).getBarIds();
		LoadInformation info = ((TransitLoadSheetVO) vo).getTransitLoadInformation();

		Deliver deliver = new Deliver();
		AgencyInfo agencyService = new Transit();

		String transitName = agencyService.getName(info.getAgencyId());
		String destination = agencyService.getId(info.getDestinationId());
		for (String barId : barIds) {
			String trail = transitName + "已发出，下一站" + info.getDestinationId() + " " + info.getTime();
			deliver.updateDeliverInfo(barId, trail, DeliveryState.TRANSIT_OUT, destination);
		}

		// 保存条形码号供到达单使用
		BarIdList idService = new BarIdList();
		ArrayList<String> ids = new ArrayList<>();
		ids.addAll(barIds);
		BarIdsPO list = new BarIdsPO(ids, info.getTransportId(), transitName, destination);
		list.setType(TransportType.CAR);
		idService.addBarIds(list);

		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		TransitLoadSheetPO receipt = (TransitLoadSheetPO) po;
		LoadInformation info = this.copyInfo(receipt.getTransitLoadInformation());
		TransitLoadSheetVO vo = new TransitLoadSheetVO();
		vo.setTransitLoadInformation(info);
		ArrayList<String> barIds = new ArrayList<>();
		barIds.addAll(receipt.getBarIds());
		vo.setBarIds(barIds);
		vo.setId(po.getId());
		vo.setMakePerson(po.getMakePerson());
		vo.setMakeTime(po.getMakeTime());
		vo.setType(po.getType());
		return vo;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		TransitLoadSheetVO receipt = (TransitLoadSheetVO) vo;
		LoadInformation info = this.copyInfo(receipt.getTransitLoadInformation());
		TransitLoadSheetPO po = new TransitLoadSheetPO();
		po.setTransitLoadInformation(info);
		ArrayList<String> barIds = new ArrayList<>();
		barIds.addAll(receipt.getBarIds());
		po.setBarIds(barIds);
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		TransitLoadSheetVO receipt = (TransitLoadSheetVO) vo;
		LoadInformation info = ((TransitLoadSheetVO) vo).getTransitLoadInformation();
		String message = "到达地：" + info.getDestinationId() + StringTool.nextLine();
		message = message + "装运订单：" + StringTool.nextLine();
		for (String barId : receipt.getBarIds()) {
			message = message + "	" + barId + StringTool.nextLine();
		}
		return message;
	}

}
