package nju.sec.yz.ExpressSystem.data.mock_object;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;
import nju.sec.yz.ExpressSystem.po.ReceiptPO;

/**
 * 
 * @author zhangqi
 *
 */
public class ReceiptDataMockObject implements ReceiptDataService{

	@Override
	public ResultMessage insert(ReceiptPO rpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ReceiptPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new ReceiptPO();
	}

	@Override
	public ResultMessage update(ReceiptPO rpo) throws RemoteException {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}


	@Override
	public ArrayList<ReceiptPO> findByType(ReceiptType type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReceiptPO> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
