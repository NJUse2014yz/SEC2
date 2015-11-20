package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptService;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
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
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage make(ReceiptVO vo) {
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


	@Override
	public ResultMessage isValid(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
