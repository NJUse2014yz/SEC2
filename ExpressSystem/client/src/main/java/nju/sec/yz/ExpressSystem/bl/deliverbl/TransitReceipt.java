package nju.sec.yz.ExpressSystem.bl.deliverbl;


import java.util.List;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitInformation;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
/**
 * 中转单
 * 中转中心到中转中心
 * @author 周聪
 *
 */
public class TransitReceipt {

	
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

	
	/**
	 * 生成receiptId
	 */
	public String creatReceiptID(String positionID){
		ReceiptID id=new ReceiptID();
		String receiptID=id.getID(positionID, IdType.TRANSIT_RECEIPT);
		return receiptID;
	}
	
	/**
	 * 从user获得当前的中转中心id
	 * 中转中心业务员编号规则：中转中心编号+B+000三位数字
	 */
	public String getCurrentTransitID(){
		String positionerID=this.getMakePersonId();
		String positionID=positionerID.split("B")[0];
		return positionID;
	}
	
	public String getMakePersonId(){
		User user=new User();
		String id=user.getCurrentID();
		return id;
	}
	
	public ResultMessage isValid(TransitInformation info) {
		if(!ValidHelper.isBeforeDate(info.getTime()))
			return new ResultMessage(Result.FAIL,"看看时间输错了没~");
		List<String> ids=info.getBarIds();
		for(String barId:ids){
			if(!ValidHelper.isBarId(barId))
				return new ResultMessage(Result.FAIL,"亲，咱们的订单号是十位数字哟~");
		}
		return new ResultMessage(Result.SUCCESS);
	}


}
