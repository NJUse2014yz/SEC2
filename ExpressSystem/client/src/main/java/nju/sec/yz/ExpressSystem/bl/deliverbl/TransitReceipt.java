package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.managerbl.Agency;
import nju.sec.yz.ExpressSystem.bl.managerbl.AgencyInfo;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityConst;
import nju.sec.yz.ExpressSystem.bl.managerbl.CityDistanceService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitInformation;
import nju.sec.yz.ExpressSystem.po.BarIdsPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 中转单 中转中心到中转中心
 * 辅助工具类
 * @author 周聪
 *
 */
public class TransitReceipt {

	/**
	 * 保存条形码号供到达单使用
	 */
	public void saveBarIds(List<String> barIDs,String receiptId){
		BarIdList barIds=new BarIdList();
		ArrayList<String> ids2=new ArrayList<>();
		ids2.addAll(barIDs);
		BarIdsPO list=new BarIdsPO(ids2, receiptId);
		barIds.addBarIds(list);
	}

	public double distance(String beginTransit, String endTransit) {
		// 出发地和到达地都是中转中心名称
		CityDistanceService city = new CityConst();
		AgencyInfo agency = new Agency();
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
		for (String barId : ids) {
			if (!ValidHelper.isBarId(barId))
				return new ResultMessage(Result.FAIL, "亲，咱们的订单号是十位数字哟~");
		}
		return new ResultMessage(Result.SUCCESS);
	}

	public ResultMessage make(ReceiptVO vo) {
		
		return null;
	}
	
	public ResultMessage approve(ReceiptVO vo) {
		
		return null;
	}
	
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ReceiptPO convertToPO(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
