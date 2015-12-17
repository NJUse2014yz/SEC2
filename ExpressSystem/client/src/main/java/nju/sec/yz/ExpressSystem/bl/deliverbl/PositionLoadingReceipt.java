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
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.OfficeLoadSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 营业厅装车单的领域模型
 * 
 * @author 周聪
 *
 */
public class PositionLoadingReceipt implements ReceiptService {

	@Override
	public ResultMessage make(ReceiptVO vo) {
		OfficeLoadSheetVO receipt = (OfficeLoadSheetVO) vo;
		LoadInformation info = receipt.getOfficeLoadInformation();
		List<String> barIDs = receipt.getBarIds();

		if (info.getTime() == null)
			info.setTime(TimeTool.getDate());

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
		String positionID = this.getCurrentPositionID();
		String transportID = this.createTransportID(positionID);
		info.setAgencyId(positionID);
		info.setTransportId(transportID);

		// 计算运费
		double fare = this.cost(barIDs.size());
		info.setFare(fare);

		// 创建po提交给receiptlist
		OfficeLoadSheetPO po = new OfficeLoadSheetPO();
		LoadInformation information = this.copyInfo(info);
		po.setOfficeLoadInformation(information);

		po.setId(transportID);// 汽运编号作为单据id
		ArrayList<String> ids = new ArrayList<>();
		ids.addAll(barIDs);
		po.setBarIds(ids);
		po.setFare(fare);
		po.setType(ReceiptType.POSITION_LOADING_RECEIPT);
		po.setMakeTime(TimeTool.getDate());
		po.setMakePerson(this.getMakePersonId());

		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		// 更新物流信息
		Deliver deliver = new Deliver();
		for (String barId : receipt.getBarIds()) {
			deliver.submit(barId);
		}

		return new ResultMessage(Result.SUCCESS, fare + " " + transportID);
	}

	/**
	 * 生成汽运编号
	 */
	private String createTransportID(String positionID) {
		ReceiptID id = new ReceiptID();
		String transportID = id.getID(positionID, IdType.POSITION_TRANSPORT);
		return transportID;
	}

	/**
	 * 从user获得当前的营业厅id 营业厅业务员编号规则：营业厅编号+C+000三位数字
	 */
	private String getCurrentPositionID() {
		User user = new User();
		String positionerID = user.getCurrentID();
		String positionID = positionerID.split("C")[0];
		return positionID;
	}

	/**
	 * 获得当前营业厅
	 */
	public PositionVO getCurrentPosition() {
		Position position = new Position();

		String positionId = this.getCurrentPositionID();

		return position.findPosition(positionId);

	}

	public List<String> getValidAgency() {
		List<String> agencies = new ArrayList<>();
		PositionVO current = this.getCurrentPosition();
		if (current == null)
			return agencies;

		// 获得所属中转中心
		Transit transitService = new Transit();
		TransitVO transit = transitService.observeTransit(current.transitId);
		agencies.add(transit.getName());

		// 添加所属中转中心的其他营业厅
		for (PositionVO position : transit.getPositions()) {
			agencies.add(position.name);
		}

		agencies.remove(current.name);

		return agencies;
	}

	private String getMakePersonId() {
		User user = new User();
		String id = user.getCurrentID();
		return id;
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

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		ResultMessage validResult = new ResultMessage(Result.FAIL);

		OfficeLoadSheetVO receipt = (OfficeLoadSheetVO) vo;

		String date = receipt.getOfficeLoadInformation().getTime();

		if (!ValidHelper.isBeforeDate(date))
			return new ResultMessage(Result.FAIL, "不能选超过今天的时间哦~");

		// 验证barid
		List<String> barIDs = receipt.getBarIds();

		if (barIDs == null || barIDs.size() == 0)
			return new ResultMessage(Result.FAIL, "还没填订单号~");

		// 验证是否有重复
		Set<String> idSet = new HashSet<>(barIDs);
		if (idSet.size() > barIDs.size())
			return new ResultMessage(Result.FAIL, "有条形码号重复了~");

		for (String barID : barIDs) {
			if (!ValidHelper.isBarId(barID)) {
				validResult.setMessage("亲，咱们的订单号是十位数字哟~");
				return validResult;
			}

		}

		// 验证info
		LoadInformation info = receipt.getOfficeLoadInformation();

		if (!ValidHelper.isBeforeDate(info.getTime()))
			return new ResultMessage(Result.FAIL, "看看时间是不是输错了~");
		Car car = new Car();
		if (!car.isId(info.getCarId()))
			return new ResultMessage(Result.FAIL, "看看车辆ID输对了没哦");

		return new ResultMessage(Result.SUCCESS);
	}

	private boolean isRightTrail(String barId) {
		String currentAgency = this.getCurrentPositionID();

		Deliver deliver = new Deliver();
		DeliverStateVO vo = deliver.getDeliverState(barId);

		
		if (vo == null)// 物流信息不存在
			return false;
		else if (!vo.nextAgency.equals(currentAgency))// 下个机构id不是当前机构
			return false;
		// 营业厅装车单在快递员揽件或者营业厅有到达单之后
		else if (vo.state != DeliveryState.GATHER && vo.state != DeliveryState.OFFICE_IN)
			return false;
		return true;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		List<String> barIds = ((OfficeLoadSheetVO) vo).getBarIds();
		LoadInformation info = ((OfficeLoadSheetVO) vo).getOfficeLoadInformation();

		Deliver deliver = new Deliver();
		AgencyInfo agencyService = new Transit();
		
		String positionName = agencyService.getName(info.getAgencyId());
		
		String nextAgency = agencyService.getId(info.getDestinationId());// 获得下一个轨迹id
		for (String barId : barIds) {
			String trail = positionName + "已发出，下一站" + info.getDestinationId() + " " + info.getTime();
			deliver.updateDeliverInfo(barId, trail, DeliveryState.OFFICE_OUT, nextAgency);
		}

		// 保存条形码号供到达单使用
		BarIdList idService = new BarIdList();
		ArrayList<String> ids = new ArrayList<>();
		ids.addAll(barIds);
		BarIdsPO list = new BarIdsPO(ids, info.getTransportId(), positionName, nextAgency);
		list.setType(TransportType.CAR);
		idService.addBarIds(list);

		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		OfficeLoadSheetPO receipt = (OfficeLoadSheetPO) po;
		LoadInformation info = this.copyInfo(receipt.getOfficeLoadInformation());
		OfficeLoadSheetVO vo = new OfficeLoadSheetVO();
		vo.setOfficeLoadInformation(info);
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
		OfficeLoadSheetVO receipt = (OfficeLoadSheetVO) vo;
		LoadInformation info = this.copyInfo(receipt.getOfficeLoadInformation());
		OfficeLoadSheetPO po = new OfficeLoadSheetPO();
		po.setOfficeLoadInformation(info);
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
		OfficeLoadSheetVO receipt = (OfficeLoadSheetVO) vo;
		LoadInformation info = ((OfficeLoadSheetVO) vo).getOfficeLoadInformation();

		String message = "到达地：" + info.getDestinationId() + StringTool.nextLine();
		message = message + "装运订单：" ;
		for (String barId : receipt.getBarIds()) {
			message = message + "	" + barId + StringTool.nextLine();
		}

		return message;
	}

}
