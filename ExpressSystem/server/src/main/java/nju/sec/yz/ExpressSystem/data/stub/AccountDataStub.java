package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class AccountDataStub implements AccountDataService {

	@Override
	public ResultMessage insert(AccountPO apo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AccountPO find(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return new AccountPO("A01",0);
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage update(AccountPO apo) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage init() throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<AccountPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<AccountPO> array=new ArrayList<AccountPO>();
		array.add(new AccountPO("A01",0));
		array.add(new AccountPO("A02",5000));
		return array;
	}

}
