package nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice;

import java.rmi.Remote;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.SendSheetPO;
import java.rmi.RemoteException;
/**
 * 保存寄件单信息
 * @author 周聪
 *
 */
public interface OrderDataService extends Remote{
	public ResultMessage add(SendSheetPO po) throws RemoteException;
	public SendSheetPO get(String barID) throws RemoteException;
}
