package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.OutInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 付款单的领域模型对象
 * 
 * @author 周聪
 *
 */
public class Payment implements ReceiptService {

	private OutDataService data;

	public Payment() {
		try {
			data = DatafactoryProxy.getOutDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
		OutVO receipt = (OutVO) vo;
		OutInformation info = receipt.getOutInformation();

		// 验证
		ResultMessage validResult = this.isValid(receipt);
		if (validResult.getResult() == Result.FAIL)
			return validResult;
		
		//id
		String accountancyId=this.accountancyId();
		String receiptId=this.createId(accountancyId);
		
		//po
		OutPO po=new OutPO();
		OutInformation information=new OutInformation(info);
		po.setOutInformation(information);
		po.setMakeTime(TimeTool.getDate());
		po.setMakePerson(accountancyId);
		po.setId(receiptId);
		po.setType(ReceiptType.PAYMENT);
		
		//提交
		ReceiptSaveService list=new ReceiptList();
		ResultMessage message=list.saveReceipt(po);
		
		return message;
	}
		

	/**
	 * 付款单单号规则：财务人员编号+f+日期+000三位数字
	 */
	private String createId(String accountancyId){
		ReceiptID counter=new ReceiptID();
		String id=counter.getID(accountancyId, IdType.PAYMENT);
		return id;
	}
	
	private String accountancyId(){
		UserInfo user=new User();
		return user.getCurrentID();
	}
	
	@Override
	public ReceiptVO show(ReceiptPO po) {
		OutPO receipt=(OutPO)po;
		OutInformation info=new OutInformation(receipt.getOutInformation());
		
		OutVO vo=new OutVO();
		vo.setOutInformation(info);
		vo.copy(receipt);
		
		return vo;
	}


	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		OutVO receipt=(OutVO)vo;
		
		OutPO po=new OutPO();
		OutInformation info=new OutInformation(receipt.getOutInformation());
		po.setOutInformation(info);
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}

	@Override
	public ResultMessage isValid(ReceiptVO vo) {

		OutVO receipt = (OutVO) vo;
		OutInformation info = receipt.getOutInformation();

		if (!ValidHelper.isBeforeDate(info.getDate()))
			return new ResultMessage(Result.FAIL, "看看日期是不是输错了~");
		if (info.getNum() <= 0)
			return new ResultMessage(Result.FAIL, "付款金额必须是正数~");
		Account account = new Account();
		if (account.observeAccount(info.getAccount()) == null)
			return new ResultMessage(Result.FAIL, "账户不存在~");

		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		OutVO receipt=(OutVO)vo;
		OutInformation info=receipt.getOutInformation();
		
		OutPO po=(OutPO)this.convertToPO(receipt);
		this.addPayment(po);
		
		Account account=new Account();
		account.updatePayment(info.getAccount(), info.getNum());
		
		return new ResultMessage(Result.SUCCESS);
	}
	
	/**
	 * 保存付款单信息
	 */
	public ResultMessage addPayment(OutPO po) {
		ResultMessage message=new ResultMessage(Result.FAIL);
		
		try {
			message=data.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return message;
	}
	
	public double getAllPayment(){
		double out=0.0;
		
		try {
			List<OutPO> outs=data.findAll();
			for(OutPO po:outs){
				out=out+po.getOutInformation().getNum();
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return out;
	}
	
	public List<OutVO> getByTime(String begin,String end){
		List<OutVO> results=new ArrayList<>();
		
		try {
			List<OutPO> pos=data.findByTime(begin, end);
			for(OutPO po:pos){
				OutVO vo=(OutVO)this.show(po);
				results.add(vo);
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		
		return results;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		OutVO receipt=(OutVO)vo;
		OutInformation info=receipt.getOutInformation();
		String message="付款金额："+info.getAccount()+StringTool.nextLine();
		message=message+"付款原因："+info.getReason()+StringTool.nextLine();
		return message;
	}
}
