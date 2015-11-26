package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.ArrayList;
import java.util.List;
import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;

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

		// 生成各种id
		String userId = this.getCurrentUserId();
		String transitId = this.getCurrentTransitId(userId);
		String transportId = this.getTransportId(transitId);
		String receiptId = this.getReceiptId(transitId);

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
		po.setId(receiptId);
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.TRANSIT_LOADING_RECEIPT);

		// 提交给receiptlist
		ReceiptSaveService receiptList = new ReceiptList();
		ResultMessage saveResult = receiptList.saveReceipt(po);
		if (saveResult.getResult() == Result.FAIL)
			return saveResult;

		// 保存条形码号供到达单使用
		BarIdList barIds = new BarIdList();
		ArrayList<String> barIdsCopy2 = new ArrayList<>();
		barIdsCopy2.addAll(barIDs);
		BarIdsPO list = new BarIdsPO(barIdsCopy2, receiptId);
		barIds.addBarIds(list);
		
		return new ResultMessage(Result.SUCCESS,fare+"");
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

	private String getReceiptId(String transitId) {
		ReceiptID id = new ReceiptID();
		String receiptID = id.getID(transitId, IdType.TRANSIT_LOADING_RECEIPT);
		return receiptID;
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

	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		ResultMessage validResult = new ResultMessage(Result.FAIL);

		TransitLoadSheetVO receipt = (TransitLoadSheetVO) vo;

		// 验证barid
		List<String> barIDs = receipt.getBarIds();
		for (String barID : barIDs) {
			if (!ValidHelper.isBarId(barID)) {
				validResult.setMessage("亲，咱们的订单号是十位数字哟~");
				return validResult;
			}
		}

		// 验证info
		LoadInformation info = receipt.getTransitLoadInformation();
		if (!ValidHelper.isValidDate(info.getTime()))
			validResult.setMessage("看看时间是不是输错了~");
		Car car = new Car();
		if (!car.isId(info.getCarId()))
			validResult.setMessage("看看车辆ID输对了没哦");

		return validResult;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		TransitLoadSheetPO receipt=(TransitLoadSheetPO)po;
		LoadInformation info=this.copyInfo(receipt.getTransitLoadInformation());
		TransitLoadSheetVO vo=new TransitLoadSheetVO();
		vo.setTransitLoadInformation(info);
		ArrayList<String>  barIds=new ArrayList<>();
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
		TransitLoadSheetVO receipt=(TransitLoadSheetVO)vo;
		LoadInformation info=this.copyInfo(receipt.getTransitLoadInformation());
		TransitLoadSheetPO po=new TransitLoadSheetPO();
		po.setTransitLoadInformation(info);
		ArrayList<String>  barIds=new ArrayList<>();
		barIds.addAll(receipt.getBarIds());
		po.setBarIds(barIds);
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

}
