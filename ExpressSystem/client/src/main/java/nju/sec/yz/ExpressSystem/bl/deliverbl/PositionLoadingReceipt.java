package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.managerbl.Price;
import nju.sec.yz.ExpressSystem.bl.managerbl.PriceService;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.OfficeLoadSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 营业厅装车单的领域模型
 * @author 周聪
 *
 */
public class PositionLoadingReceipt implements ReceiptService{

	@Override
	public ResultMessage make(ReceiptVO vo) {
		OfficeLoadSheetVO receipt=(OfficeLoadSheetVO)vo;
		LoadInformation info=receipt.getOfficeLoadInformation();
		List<String> barIDs=receipt.getBarIds();
		
		//验证
		ResultMessage validResult=isValid(receipt);
		if(validResult.getResult()==Result.FAIL)
			return validResult;
		
		//生成各种id
		String positionID=this.getCurrentPositionID();
		String transportID=this.createTransportID(positionID);
		info.setAgencyId(positionID);
		info.setTransportId(transportID);
		
		//计算运费
		double fare=this.cost(barIDs.size());
		info.setFare(fare);
		
		//创建po提交给receiptlist
		OfficeLoadSheetPO po=new OfficeLoadSheetPO();
		LoadInformation information=this.copyInfo(info);
		po.setOfficeLoadInformation(information);
		String receiptID=this.creatReceiptID(positionID);
		po.setId(receiptID);
		ArrayList<String> ids=new ArrayList<>();
		ids.addAll(barIDs);
		po.setBarIds(ids);
		po.setFare(fare);
		po.setType(ReceiptType.POSITION_LOADING_RECEIPT);
		po.setMakeTime(TimeTool.getDate());
		po.setMakePerson(this.getMakePersonId());
		
		ReceiptSaveService receiptList=new ReceiptList();
		ResultMessage saveResult=receiptList.saveReceipt(po);
		if(saveResult.getResult()==Result.FAIL)
			return saveResult;
		
		//保存条形码号供到达单使用
		BarIdList barIds=new BarIdList();
		ArrayList<String> ids2=new ArrayList<>();
		ids2.addAll(barIDs);
		BarIdsPO list=new BarIdsPO(ids2, receiptID);
		barIds.addBarIds(list);
		
		return new ResultMessage(Result.SUCCESS,fare+" "+transportID);
	}
	
	
	/**
	 * 生成receiptId
	 */
	private String creatReceiptID(String positionID){
		ReceiptID id=new ReceiptID();
		String receiptID=id.getID(positionID, IdType.POSITION_LOADING_RECEIPT);
		return receiptID;
	}
	
	/**
	 * 生成汽运编号
	 */
	private String createTransportID(String positionID){
		ReceiptID id=new ReceiptID();
		String transportID=id.getID(positionID, IdType.POSITION_TRANSPORT);
		return transportID;
	}
	
	/**
	 * 从user获得当前的营业厅id
	 * 营业厅业务员编号规则：营业厅编号+C+000三位数字
	 */
	private String getCurrentPositionID(){
		User user=new User();
		String positionerID=user.getCurrentID();
		String positionID=positionerID.split("C")[0];
		return positionID;
	}
	
	/**
	 * 获得当前营业厅
	 */
	public PositionVO getCurrentPosition(){
		Position position=new Position();
		
		String positionId=this.getCurrentPositionID();
		
		return position.findPosition(positionId);
		
	}
	
	public List<String> getValidAgency(){
		List<String> agencies=new ArrayList<>();
		PositionVO current=this.getCurrentPosition();
		
		//获得所属中转中心
		Transit transitService=new Transit();
		TransitVO transit=transitService.observeTransit(current.transitId);
		agencies.add(transit.getName());
		
		//添加所属中转中心的其他营业厅
		for(PositionVO position:transit.getPositions()){
			agencies.add(position.name);
		}
		
		agencies.remove(current.name);
		
		return agencies;
	}
	
	private String getMakePersonId(){
		User user=new User();
		String id=user.getCurrentID();
		return id;
	}
	/**
	 * 假设100个包裹为1吨
	 */
	private double cost(int num){
		double weight=num/100.0;
		double distance=CityConst.DISTANCE_OF_POSITION;
		PriceService priceService=new Price();
		double price=priceService.getCarPrice();
		double cost=weight*distance*price;
		return cost;
	}
	
	private LoadInformation copyInfo(LoadInformation info){
		String position=info.getAgencyId();
		String car=info.getCarId();
		String destination=info.getDestinationId();
		double fare=info.getFare();
		String officer=info.getOfficerId();
		String time=info.getTime();
		String transportId=info.getTransportId();
		String driver=info.getDriverId();
		
		LoadInformation information = new LoadInformation(time, position, transportId, destination, car, officer,
														driver, fare);
		return information;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		ResultMessage validResult=new ResultMessage(Result.FAIL);
		
		OfficeLoadSheetVO receipt=(OfficeLoadSheetVO)vo;
		
		//验证barid
		List<String> barIDs=receipt.getBarIds();
		
		//验证是否有重复
		Set<String> idSet=new HashSet<>(barIDs);
		if(idSet.size()>barIDs.size())
			return new ResultMessage(Result.FAIL,"有条形码号重复了~");
		
		Deliver deliver=new Deliver();
		for(String barID:barIDs){
			if(!ValidHelper.isBarId(barID)){
				validResult.setMessage("亲，咱们的订单号是十位数字哟~");
				return validResult;
			}
			//判断系统中是否存在该条形码号的物流信息
			if(deliver.checkDeliver(barID)==null){
				validResult.setMessage("系统中还没有订单"+barID+"的信息哦");
				return validResult;
			}
		}
		
		//验证info
		LoadInformation info=receipt.getOfficeLoadInformation();
		if(!ValidHelper.isBeforeDate(info.getTime()))
			validResult.setMessage("看看时间是不是输错了~");
		Car car=new Car();
		if(!car.isId(info.getCarId()))
			validResult.setMessage("看看车辆ID输对了没哦");
		
		return validResult;
	}
	
	
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		List<String> barIds = ((OfficeLoadSheetVO)vo).getBarIds();
		LoadInformation info = ((OfficeLoadSheetVO)vo).getOfficeLoadInformation();
		
		Deliver deliver = new Deliver();
		Position position=new Position();
		
		for(String barId:barIds){
			String positionName=position.findPosition(info.getAgencyId()).getName();
			String trail=positionName+"已发出，下一站"+info.getDestinationId()+" "+info.getTime();
			deliver.updateDeliverInfo(barId, trail, DeliveryState.OFFICE_OUT);
		}
		
		
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		OfficeLoadSheetPO receipt=(OfficeLoadSheetPO)po;
		LoadInformation info=this.copyInfo(receipt.getOfficeLoadInformation());
		OfficeLoadSheetVO vo=new OfficeLoadSheetVO();
		vo.setOfficeLoadInformation(info);
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
		OfficeLoadSheetVO receipt=(OfficeLoadSheetVO)vo;
		LoadInformation info=this.copyInfo(receipt.getOfficeLoadInformation());
		OfficeLoadSheetPO po=new OfficeLoadSheetPO();
		po.setOfficeLoadInformation(info);
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
