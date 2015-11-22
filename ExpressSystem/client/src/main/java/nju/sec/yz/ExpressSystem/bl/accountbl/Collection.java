package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.po.PaymentSheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
/**
 * 收款单的领域模型对象
 * @author 周聪
 *
 */
public class Collection implements ReceiptService{

	private InDataService inDaata;
	
	public Collection() {
		try {
			inDaata=DatafactoryProxy.getInDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ResultMessage make(ReceiptVO vo) {
		
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		PaymentInformation info=receipt.getPaymentInformation();
		List<String> barIds=receipt.getBarIds();//TODO
		
		//验证
		ResultMessage validResult=this.isValid(receipt);
		if(validResult.getResult()==Result.FAIL)
			return validResult;
		
		//创建po
		PaymentSheetPO po=new PaymentSheetPO();
		PaymentInformation information=this.copyInfo(info);
		po.setPaymentInformation(information);
		ArrayList<String> ids=new ArrayList<>();
		ids.addAll(barIds);
		po.setBarIds(ids);
		po.setMakeTime(TimeTool.getDate());
		po.setId(this.createId());
		po.setType(ReceiptType.COLLECTION);
		
		//提交
		ReceiptSaveService list=new ReceiptList();
		ResultMessage message=list.saveReceipt(po);
		
		return message;
	}
	
	private String createId(){
		return null;
	}
	
	private PaymentInformation copyInfo(PaymentInformation info){
		double amount=info.getAmount();
		String id=info.getInDeliverId();
		String time=info.getTime();
		
		PaymentInformation information=new PaymentInformation();
		information.setAmount(amount);
		information.setInDeliverId(id);
		information.setTime(time);
		
		return information;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		ResultMessage validResult=new ResultMessage(Result.FAIL);
		
		List<String> barIds=receipt.getBarIds();
		PaymentInformation info=receipt.getPaymentInformation();
		
		//barIds
		
		
		//info
		UserInfo user=new User();
		if(!ValidHelper.isValidDate(info.getTime())){
			validResult.setMessage("看看时间是不是输错了~");
		}else if(!user.isUser(info.getInDeliverId())){
			validResult.setMessage("快递员不存在~");
		}else{
			validResult.setResult(Result.SUCCESS);
		}
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
	/**
	 * 保存收款单信息
	 */
	private ResultMessage addCollection(PaymentSheetPO po){
		return null;
	}


	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}


}
