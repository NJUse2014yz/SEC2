package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.List;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 期初建账的具体实现
 * @author 周聪
 */
public class Initial {
	
	private boolean isFinished=false;

	//中转中心
	private List<TransitVO> transits;

	//营业厅
	private List<PositionVO> positions;
	
	//人员
	private List<StaffVO> staffs;

	//车辆
	private List<CarVO> cars;

	//银行账户
	private List<AccountVO> accounts;

	//库存
	private List<InventoryInSheetVO> inventories;

	private AccountBookDataService accountBookData;

	public Initial() {
		try {
			accountBookData = DatafactoryProxy.getAccountBookDataService();
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
	}
	
	
	public ResultMessage start() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addStaff(StaffVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addTransit(TransitVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addPosition(PositionVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addCar(CarVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addStock(InventoryInSheetVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage finish() {
		isFinished=true;
		
		
		return null;
	}

	public InitialVO observeInitial() {
		// TODO Auto-generated method stub
		return null;
	}

}
