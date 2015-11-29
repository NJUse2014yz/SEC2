package nju.sec.yz.ExpressSystem.data.stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.po.AgencyPO;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryListPO;
import nju.sec.yz.ExpressSystem.po.StaffPO;
import nju.sec.yz.ExpressSystem.po.UserPO;

/**
 * 
 * @author zhangqi
 *
 */
public class AccountBookStub implements AccountBookDataService{

	@Override
	public ArrayList<AccountBookPO> findAll() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<AccountBookPO> array=new ArrayList<AccountBookPO>();
		array.add(new AccountBookPO(new AgencyPO("000", "000", "总公司"), new ArrayList<StaffPO>(), new ArrayList<CarPO>(),
				new ArrayList<AccountPO>(), new ArrayList<InventoryListPO>(),"f"));
		return array;

	}

	@Override
	public AccountBookPO init() throws RemoteException {
		// TODO 自动生成的方法存根
		return new AccountBookPO(new AgencyPO("000", "000", "总公司"), new ArrayList<StaffPO>(), new ArrayList<CarPO>(),
				new ArrayList<AccountPO>(), new ArrayList<InventoryListPO>(),"f");
	}

	@Override
	public ResultMessage update(AccountBookPO abp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage insert(AccountBookPO abp) throws RemoteException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public AccountBookPO find(String id) throws RemoteException {
		return new AccountBookPO(new AgencyPO("000", "000", "总公司"), new ArrayList<StaffPO>(), new ArrayList<CarPO>(),
				new ArrayList<AccountPO>(), new ArrayList<InventoryListPO>(),"f");
	}
	

}
