package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.List;



/**
 * 
 * @author 周聪
 * 账目信息：机构、人员、车辆、库存、 银行账户信息（名称，余额）
 */
public class AccountBookPO implements Serializable{

	
	private String id;
	
	//机构
	private List<TransitPO> transit;
	
	//营业厅
	private List<PositionPO> position;
	
	//人员
	private List<StaffPO> staffs;
	
	//车辆
	private List<CarPO> cars;

	//银行账户
	private List<AccountPO> accounts;
	
	//TODO 库存
	private List<InventoryListPO> inventories;

	public AccountBookPO(String id, List<TransitPO> transit, List<PositionPO> position, List<StaffPO> staffs,
			List<CarPO> cars, List<AccountPO> accounts, List<InventoryListPO> inventories) {
		super();
		this.id = id;
		this.transit = transit;
		this.position = position;
		this.staffs = staffs;
		this.cars = cars;
		this.accounts = accounts;
		this.inventories = inventories;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TransitPO> getTransit() {
		return transit;
	}

	public void setTransit(List<TransitPO> transit) {
		this.transit = transit;
	}

	public List<PositionPO> getPosition() {
		return position;
	}

	public void setPosition(List<PositionPO> position) {
		this.position = position;
	}

	public List<StaffPO> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffPO> staffs) {
		this.staffs = staffs;
	}

	public List<CarPO> getCars() {
		return cars;
	}

	public void setCars(List<CarPO> cars) {
		this.cars = cars;
	}

	public List<AccountPO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountPO> accounts) {
		this.accounts = accounts;
	}

	public List<InventoryListPO> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryListPO> inventories) {
		this.inventories = inventories;
	}
	
	
}
