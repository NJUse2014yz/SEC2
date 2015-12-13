package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.managerbl.AgencyInfo;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityDistanceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitInformation;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;

/**
 * 中转单 中转中心到中转中心 辅助工具类
 * 
 * @author 周聪
 *
 */
public class TransitReceiptHelper {

	/**
	 * 保存条形码号供到达单使用
	 */
	public void saveBarIds(List<String> barIDs, String receiptId, String destinationName) {
		// 更新物流信息
		Deliver deliver = new Deliver();
		for (String barId : barIDs) {
			deliver.submit(barId);
		}

		BarIdList barIds = new BarIdList();
		ArrayList<String> ids2 = new ArrayList<>();
		ids2.addAll(barIDs);
		AgencyInfo agencyService = new Transit();
		String fromAgency = agencyService.getName(getCurrentTransitID());// 出发地名称
		String destination = agencyService.getId(destinationName);// 到达地id
		BarIdsPO list = new BarIdsPO(ids2, receiptId, fromAgency, destination);
		barIds.addBarIds(list);
	}

	public double distance(String beginTransit, String endTransit) {
		// 出发地和到达地都是中转中心名称
		CityDistanceService city = new CityConst();
		AgencyInfo agency = new Transit();
		String beginPlace = agency.getTrancitLocation(beginTransit);
		String endPlace = agency.getTrancitLocation(endTransit);
		double distance = city.getDistance(beginPlace, endPlace);
		return distance;
	}

	/**
	 * 生成receiptId
	 */
	public String creatReceiptID(String positionID) {
		ReceiptID id = new ReceiptID();
		String receiptID = id.getID(positionID, IdType.TRANSIT_RECEIPT);
		return receiptID;
	}

	/**
	 * 从user获得当前的中转中心id 中转中心业务员编号规则：中转中心编号+B+000三位数字
	 */
	public String getCurrentTransitID() {
		String positionerID = this.getMakePersonId();
		String positionID = positionerID.split("B")[0];
		return positionID;
	}

	public String getMakePersonId() {
		User user = new User();
		String id = user.getCurrentID();
		return id;
	}

	public ResultMessage isValid(TransitInformation info) {
		if (!ValidHelper.isBeforeDate(info.getTime()))
			return new ResultMessage(Result.FAIL, "看看时间输错了没~");

		List<String> ids = info.getBarIds();
		Set<String> idCopy = new HashSet<>(ids);
		if (idCopy.size() < ids.size())
			return new ResultMessage(Result.FAIL, "有条形码号重复了~");

		Deliver deliver = new Deliver();
		for (String barId : ids) {
			if (!ValidHelper.isBarId(barId))
				return new ResultMessage(Result.FAIL, "亲，咱们的订单号是十位数字哟~");

			// 判断系统中是否存在该条形码号的物流信息
			if (deliver.checkDeliver(barId) == null) {
				return new ResultMessage(Result.FAIL, "系统中还没有订单" + barId + "的信息哦");
			}

			if (isRightTrail(barId))
				return new ResultMessage(Result.FAIL, "订单号是不是填错了~");
		}
		return new ResultMessage(Result.SUCCESS);
	}

	private boolean isRightTrail(String barId) {
		String currentAgency = this.getCurrentTransitID();

		Deliver deliver = new Deliver();
		DeliverStateVO vo = deliver.getDeliverState(barId);

		if (vo == null)// 物流信息不存在
			return false;
		else if (!vo.nextAgency.equals(currentAgency))// 下个机构id不是当前机构
			return false;
		// 营业厅装车单在快递员揽件或者营业厅有到达单之后
		else if (vo.state != DeliveryState.INVENTORY_OUT)
			return false;
		return true;
	}

	public ResultMessage approve(ReceiptVO vo) {
		TransitInformation info = ((TransitSheetVO) vo).getTransitInformation();
		List<String> barIds = info.getBarIds();

		//
		AgencyInfo agency = new Transit();
		Deliver deliver = new Deliver();
		String destinationId = agency.getId(info.getDestination());
		String trail = info.getDeparture() + "已发出，下一站" + info.getDestination();
		trail = trail + " " + info.getTime();
		for (String barId : barIds) {
			deliver.updateDeliverInfo(barId, trail, DeliveryState.TRANSIT_OUT, destinationId);
		}

		return new ResultMessage(Result.SUCCESS);
	}

	public String showMessage(TransitInformation info) {
		String message = "到达地：" + info.getDestination() + StringTool.nextLine();

		message = message + "装运订单：" + StringTool.nextLine();
		for (String barId : info.getBarIds()) {
			message = message + "	" + barId + StringTool.nextLine();
		}

		return message;
	}

}
