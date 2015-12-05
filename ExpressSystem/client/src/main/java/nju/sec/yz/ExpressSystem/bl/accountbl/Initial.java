package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.inventorybl.Inventory;
import nju.sec.yz.ExpressSystem.bl.managerbl.Position;
import nju.sec.yz.ExpressSystem.bl.managerbl.Staff;
import nju.sec.yz.ExpressSystem.bl.managerbl.Transit;
import nju.sec.yz.ExpressSystem.bl.receiptbl.ReceiptID;
import nju.sec.yz.ExpressSystem.bl.tool.LogTool;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.client.DatafactoryProxy;
import nju.sec.yz.ExpressSystem.client.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.common.IdType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.po.AccountBookPO;
import nju.sec.yz.ExpressSystem.po.AccountPO;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.po.InventoryInSheetPO;
import nju.sec.yz.ExpressSystem.po.PositionPO;
import nju.sec.yz.ExpressSystem.po.StaffPO;
import nju.sec.yz.ExpressSystem.po.TransitPO;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 期初建账的具体实现
 * 
 * @author 周聪
 */
public class Initial {

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
		transits = new ArrayList<>();
		positions = new ArrayList<>();
		staffs = new ArrayList<>();
		cars = new ArrayList<>();
		accounts = new ArrayList<>();
		inventories = new ArrayList<>();
	}

	public ResultMessage addStaff(List<StaffVO> vo) {
		staffs.addAll(vo);
		Initialable<StaffVO, StaffPO> staffService = new Staff();
		ResultMessage message = staffService.init(vo);
		return message;
	}

	public ResultMessage addTransit(List<TransitVO> vo) {
		transits.addAll(vo);
		Initialable<TransitVO, TransitPO> transitService = new Transit();
		ResultMessage message = transitService.init(vo);
		return message;
	}


	public ResultMessage addCar(List<CarVO> vo) {
		cars.addAll(vo);
		Initialable<CarVO, CarPO> car = new Car();
		ResultMessage message = car.init(cars);
		return message;
	}

	public ResultMessage addAccount(List<AccountVO> vo) {
		accounts.addAll(vo);
		Initialable<AccountVO, AccountPO> account = new Account();
		return account.init(vo);
	}

	public ResultMessage addStock(List<InventoryInSheetVO> vo) {
		inventories.addAll(vo);
		Initialable<InventoryInSheetVO, InventoryInSheetPO> inventory = new Inventory();
		ResultMessage message = inventory.init(vo);
		return message;
	}

	public ResultMessage finish() {

		AccountBookPO initialPO=this.createPO();
		
		ResultMessage message=new ResultMessage(Result.FAIL);
		try {
			message=accountBookData.insert(initialPO);
		} catch (RemoteException e) {
			RMIExceptionHandler.handleRMIException();
			e.printStackTrace();
		}
		
		LogTool.setLog("期初建帐");

		return message;
	}

	private AccountBookPO createPO() {
		AccountBookPO initialPO = new AccountBookPO();

		Initialable<CarVO, CarPO> carService = new Car();
		List<CarPO> carPOs = this.changeVOToPO(carService, cars);
		initialPO.setCars(carPOs);

		Initialable<StaffVO, StaffPO> staffService = new Staff();
		List<StaffPO> staffPOs = this.changeVOToPO(staffService, staffs);
		initialPO.setStaffs(staffPOs);

		Initialable<InventoryInSheetVO, InventoryInSheetPO> stockService = new Inventory();
		List<InventoryInSheetPO> stockPOs = this.changeVOToPO(stockService, inventories);
		initialPO.setInventories(stockPOs);

		Initialable<AccountVO, AccountPO> accountService = new Account();
		List<AccountPO> accountPOs = this.changeVOToPO(accountService, accounts);
		initialPO.setAccounts(accountPOs);

		Initialable<TransitVO, TransitPO> transitService = new Transit();
		List<TransitPO> transitPOs = this.changeVOToPO(transitService, transits);
		initialPO.setTransit(transitPOs);
		
		initialPO.setId(this.createId());
		
		return initialPO;
	}

	
	/**
	 * 期初建帐id编号规则：财务人员编号+"i"+3位数字
	 */
	private String createId(){
		UserInfo user=new User();
		String userId=user.getCurrentID();
		ReceiptID idMaker=new ReceiptID();
		return idMaker.getID(userId, IdType.INITIAL);
	}
	
	/**
	 * 将vo列表转成po列表
	 */
	private <VO, PO> List<PO> changeVOToPO(Initialable<VO, PO> service, List<VO> vos) {
		List<PO> pos = new ArrayList<>();
		for (VO vo : vos) {
			PO po = service.changeVOToPO(vo);
			pos.add(po);
		}
		return pos;

	}


	public InitialVO observeInitial() {

		return null;
	}
	
	private InitialVO show(AccountBookPO po){
		InitialVO vo=new InitialVO();
		
		Initialable<CarVO, CarPO> carService=new Car();
		vo.cars=this.show(carService, po.getCars());
		
		Initialable<AccountVO, AccountPO> accountService=new Account();
		vo.accounts=this.show(accountService, po.getAccounts());
		
		Initialable<InventoryInSheetVO, InventoryInSheetPO> stockService=new Inventory();
		vo.inventories=this.show(stockService, po.getInventories());
		
		Initialable<TransitVO, TransitPO> transitService=new Transit();
		vo.transits=this.show(transitService, po.getTransit());
		
		Initialable<StaffVO, StaffPO> staffService=new Staff();
		vo.staffs=this.show(staffService, po.getStaffs());
		
		vo.id=po.getId();
		
		return vo;
	}
	
	/**
	 * 将po列表转成vo列表
	 */
	private <VO, PO> List<VO> show(Initialable<VO, PO> service, List<PO> pos) {
		List<VO> vos = new ArrayList<>();
		for (PO po : pos) {
			VO vo = service.show(po);
			vos.add(vo);
		}
		return vos;

	}

}
