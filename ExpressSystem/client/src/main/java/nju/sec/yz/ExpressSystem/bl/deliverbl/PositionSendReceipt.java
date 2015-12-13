package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptList;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptSaveService;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.bl.tool.StringTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.DeliverySheetPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 派件单的领域模型
 * @author 周聪
 *
 */
public class PositionSendReceipt implements ReceiptService{
	@Override
	public ResultMessage make(ReceiptVO vo) {
		
		DeliverySheetVO receipt=(DeliverySheetVO)vo;
		DeliveryInformation info=receipt.getDeliveryInformation();
		
		//验证
		ResultMessage validResult=this.isValid(receipt);
		if(validResult.getResult()==Result.FAIL)
			return validResult;
		
		//创建po
		DeliverySheetPO po=new DeliverySheetPO();
		DeliveryInformation imInformation=this.copyInfo(info);
		po.setDeliveryInformation(imInformation);
		po.setId(this.createId());
		po.setMakePerson(getDeliverId());
		po.setMakeTime(TimeTool.getDate());
		po.setType(ReceiptType.POSITION_SEND_RECEIPT);
		
		//提交给总经理
		ReceiptSaveService receiptList=new ReceiptList();
		receiptList.saveReceipt(po);
		
		//更新物流信息
		Deliver deliver=new Deliver();
		deliver.submit(info.getBarId());
		
		return new ResultMessage(Result.SUCCESS);
	}
	
	/**
	 * 自动生成派件单id
	 * @return
	 */
	private String createId(){
		String deliverId=this.getDeliverId();
		ReceiptID counter=new ReceiptID();
		String receiptId=counter.getID(deliverId, IdType.POSITION_SEND_RECEIPT);
		return receiptId;
	}
	
	/**
	 * 获得业务员id
	 * @return
	 */
	private String getDeliverId(){
		UserInfo user=new User();
		String id=user.getCurrentID();
		return id;
	}
	
	private DeliveryInformation copyInfo(DeliveryInformation info){
		DeliveryInformation information=new DeliveryInformation();
		information.setBarId(info.getBarId());
		information.setTime(info.getTime());
		information.setOutDeliverId(info.getOutDeliverId());
		return information;
	}
	
	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		Deliver deliver=new Deliver();
		
		ResultMessage validResult=new ResultMessage(Result.FAIL);
		DeliverySheetVO receipt=(DeliverySheetVO)vo;
		DeliveryInformation info=receipt.getDeliveryInformation();
		String time=info.getTime();
		String barId=info.getBarId();
		String deliverId=info.getOutDeliverId();
		
		UserInfo user=new User();
		if(!user.isUser(deliverId))
			return new ResultMessage(Result.FAIL,"快递员id是不是填错了~");
		
		if(!ValidHelper.isBeforeDate(time))
			validResult.setMessage("再看看时间是不是输错了~");
		else if(!ValidHelper.isBarId(barId))
			validResult.setMessage("亲，咱们的订单号是十位数字哟~");
		else if(deliver.checkDeliver(barId)==null){
			validResult.setMessage("亲，系统中不存在订单"+barId);
		}else if(!isRightTrail(barId))
			validResult.setMessage("订单号是不是填错了~");
		else
			validResult.setResult(Result.SUCCESS);
		return validResult;
	}
	
	private boolean isRightTrail(String barId){
		String currentAgency=this.getDeliverId().split("C")[0];
		
		Deliver deliver=new Deliver();
		DeliverStateVO vo=deliver.getDeliverState(barId);
		
		if(vo==null)//物流信息不存在
			return false;
		else if(!vo.nextAgency.equals(currentAgency))//下个机构id不是当前机构
			return false;
		//
		else if(vo.state!=DeliveryState.GATHER&&vo.state!=DeliveryState.OFFICE_IN)
			return false;
		
		
		return true;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		DeliveryInformation info=((DeliverySheetVO)vo).getDeliveryInformation();
		
		String trail="快递员正在派件中。  "+info.getTime();
		
		String currentAgency=info.getOutDeliverId();//下个轨迹为某个快递员
		
		//更新物流信息
		Deliver deliver=new Deliver();
		
		return deliver.updateDeliverInfo(info.getBarId(), trail, DeliveryState.DELIVING,currentAgency);
	}


	@Override
	public ReceiptVO show(ReceiptPO po) {
		DeliverySheetPO receipt=(DeliverySheetPO)po;
		
		DeliverySheetVO vo=new DeliverySheetVO();
		vo.setDeliveryInformation(new DeliveryInformation(receipt.getDeliveryInformation()));
		
		
		vo.copy(po);
		
		return vo;
	}

	@Override
	public ReceiptPO convertToPO(ReceiptVO vo) {
		DeliverySheetVO receipt=(DeliverySheetVO)vo;
		
		DeliverySheetPO po=new DeliverySheetPO();
		po.setDeliveryInformation(new DeliveryInformation(receipt.getDeliveryInformation()));
		po.setId(vo.getId());
		po.setMakePerson(vo.getMakePerson());
		po.setMakeTime(vo.getMakeTime());
		po.setType(vo.getType());
		
		return po;
	}

	@Override
	public String showMessage(ReceiptVO vo) {
		DeliveryInformation info=((DeliverySheetVO)vo).getDeliveryInformation();
		String message="条形码号："+info.getBarId()+StringTool.nextLine();
		message=message+"派送员id："+info.getOutDeliverId()+StringTool.nextLine();
		return null;
	}


	
}
