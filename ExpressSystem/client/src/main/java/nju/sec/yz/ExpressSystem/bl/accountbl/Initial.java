package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;

/**
 * 期初建账的具体实现
 * @author 周聪
 *
 */
public class Initial {

	private AccountBookDataService accountBookData;
	
	public Initial() {
		try {
			accountBookData=DatafactoryProxy.getAccountBookDataService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public InitialVO observeIni() {
		return null;
	}

	/**
	 * 期初建账
	 * 包括：机构、人员、车辆、库存、 银行账户信息（名称，余额）
	 */
	public ResultMessage initial(InitialVO av) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
