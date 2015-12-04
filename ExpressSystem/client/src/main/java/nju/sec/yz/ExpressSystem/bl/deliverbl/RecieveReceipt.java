package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;

/**
 * 收件单的领域模型
 * 收件单不用审批
 * @author 周聪
 *
 */
public class RecieveReceipt {


	public ResultMessage make(ReceiveVO vo) {
		
		String validResult=isValid(vo);
		if(!validResult.equals("success"))
			return new ResultMessage(Result.FAIL,validResult);
		Deliver deliver=new Deliver();
		//更新历史轨迹
		ResultMessage message=deliver.updateReceiveReceipt(vo);
		return message;
	}
	
	private String isValid(ReceiveVO vo){
		String barID=vo.getReceiveInformation().getId();
		String date=vo.getReceiveInformation().getTime();
		if(!ValidHelper.isBarId(barID))
			return "亲，咱们的订单号是十位数字哟~";

		if(!ValidHelper.isBeforeDate(date))
			return "日期是不是输错了~";

		return "success";
	}


	
}
