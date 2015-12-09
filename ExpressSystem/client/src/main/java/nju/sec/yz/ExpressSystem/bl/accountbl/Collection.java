package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.deliverbl.CollectionRecord;
import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.po.OutPO;
import nju.sec.yz.ExpressSystem.po.PaymentSheetPO;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.po.TransitLoadSheetPO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
/**
 * 收款单的领域模型对象
 * @author 周聪
 *
 */
public class Collection implements ReceiptService{

	private InDataService inData;
	
	public Collection() {
		try {
			inData=DatafactoryProxy.getInDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ResultMessage make(ReceiptVO vo) {
		
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		PaymentInformation info=receipt.getPaymentInformation();
		
		
		//验证
		ResultMessage validResult=this.isValid(receipt);
		if(validResult.getResult()==Result.FAIL)
			return validResult;
		
		//营业厅id
		info.setPositionId(info.getPositionId());
		
		//删除记录
		CollectionRecord record=new CollectionRecord();
		record.deleteRecord(receipt.getBarIds());
		
		//创建po
		PaymentSheetPO po=new PaymentSheetPO();
		PaymentInformation information=this.copyInfo(info);
		po.setPaymentInformation(information);
		po.setBarIds(receipt.getBarIds());
		po.setMakeTime(TimeTool.getDate());
		po.setMakePerson(accountancyId());
		po.setId(this.createId(info.getInDeliverId()));
		po.setType(ReceiptType.COLLECTION);
		
		//提交
		ReceiptSaveService list=new ReceiptList();
		ResultMessage message=list.saveReceipt(po);
		
		return message;
	}
	
	/**
	 * 收款单单号规则：快递员编号+k+日期+000三位数字
	 */
	private String createId(String deliverId){
		ReceiptID counter=new ReceiptID();
		String id=counter.getID(deliverId, IdType.COLLECTION);
		return id;
	}
	
	private String getPositionId(String deliverId){
		String positionId=deliverId.split("D")[0];
		return positionId;
	}
	
	private String accountancyId(){
		UserInfo user=new User();
		return user.getCurrentID();
	}
	
	private PaymentInformation copyInfo(PaymentInformation info){
		double amount=info.getAmount();
		String id=info.getInDeliverId();
		String time=info.getTime();
		String positionId=info.getPositionId();
		String account=info.getAccount();
		
		PaymentInformation information=new PaymentInformation();
		information.setAmount(amount);
		information.setInDeliverId(id);
		information.setTime(time);
		information.setPositionId(positionId);
		information.setAccount(account);
		
		return information;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		ResultMessage validResult=new ResultMessage(Result.FAIL);
		
		
		PaymentInformation info=receipt.getPaymentInformation();
		
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
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		PaymentInformation info=receipt.getPaymentInformation();
		Account account=new Account();
		account.updateCollection(info.getAccount(), info.getAmount());
		PaymentSheetPO po=(PaymentSheetPO)this.convertToPO(receipt);
		ResultMessage message=this.addCollection(po);
		return message;
	}

	@Override
	public ReceiptVO show(ReceiptPO po) {
		PaymentSheetPO receipt=(PaymentSheetPO)po;
		
		PaymentSheetVO vo=new PaymentSheetVO();
		PaymentInformation info=this.copyInfo(receipt.getPaymentInformation());
		vo.setPaymentInformation(info);
		vo.setBarIds(receipt.getBarIds());
		vo.setId(receipt.getId());
		vo.setMakePerson(po.getMakePerson());
		vo.setMakeTime(po.getMakeTime());
		vo.setType(po.getType());
		
		return vo;
	}


	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		PaymentSheetVO receipt=(PaymentSheetVO)vo;
		PaymentInformation info=this.copyInfo(receipt.getPaymentInformation());
		PaymentSheetPO po=new PaymentSheetPO();
		po.setPaymentInformation(info);
		po.setBarIds(receipt.getBarIds());
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		return po;
	}
	
	private PaymentSheetVO changeVOToPO(PaymentSheetPO po){
		PaymentSheetVO vo=(PaymentSheetVO) this.show(po);
		return vo;
	}
	
	/**
	 * 保存收款单信息
	 */
	private ResultMessage addCollection(PaymentSheetPO po){
		ResultMessage message=null;
		
		try {
			message=inData.insert(po);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return message;
	}
	/**
	 * 获得总收入
	 * @return
	 */
	public double getAllCollection(){
		List<PaymentSheetPO> pos=new ArrayList<>();
		double in=0.0;
		try {
			pos=inData.findAll();
			for(PaymentSheetPO po:pos){
				in=in+po.getPaymentInformation().getAmount();
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		return in;
		
	}
	
	public List<PaymentSheetVO> getByTime(String begin,String end){
		List<PaymentSheetPO> pos=new ArrayList<>();
		List<PaymentSheetVO> vos=new ArrayList<>();
		try {
			pos=inData.findByTime(begin, end);
			for(PaymentSheetPO po:pos){
				PaymentSheetVO vo=this.changeVOToPO(po);
				vos.add(vo);
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		return vos;
		
	}
	
	public PaymentVO getByPosition(String date,String positionId){
		List<PaymentSheetPO> pos=new ArrayList<>();
		List<PaymentSheetVO> vos=new ArrayList<>();
		double sum=0.0;
		try {
			pos=inData.findByPosition(date, positionId);
			for(PaymentSheetPO po:pos){
				PaymentSheetVO vo=this.changeVOToPO(po);
				vos.add(vo);
				sum=sum+vo.getPaymentInformation().getAmount();
			}
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		PaymentVO vo=new PaymentVO();
		vo.paymentList=vos;
		vo.sum=sum;
		return vo;
		
	}


}
