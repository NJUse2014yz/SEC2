package nju.sec.yz.ExpressSystem.bl.deliverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

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
		ResultMessage validResult=isValid(receipt);
		
		return null;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		ResultMessage validResult=new ResultMessage(Result.FAIL);
		
		OfficeLoadSheetVO receipt=(OfficeLoadSheetVO)vo;
		
		//验证barid
		List<String> barIDs=receipt.getBarIds();
		for(String barID:barIDs){
			if(!ValidHelper.isBarId(barID)){
				validResult.setMessage("亲，咱们的订单号是十位数字哟~");
				return validResult;
			}
		}
		
		//验证info
		LoadInformation info=receipt.getOfficeLoadInformation();
		if(!ValidHelper.isValidDate(info.getTime()))
			validResult.setMessage("时间格式不正确");
		
		return validResult;
	}
	
	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}


}
