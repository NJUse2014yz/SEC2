package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.List;



/**
 * 
 * @author 周聪
 * 账目信息：机构、人员、车辆、库存、 银行账户信息（名称，余额）
 */
public class AccountBookPO implements Serializable{

	
	private String date;
	
	//机构
	private List<TransitPO> transit;
	
	//人员
	private List<StaffPO> staffs;
	
	//车辆
	private List<CarPO> cars;

	//银行账户
	private List<AccountPO> accounts;
	
	//库存
	private List<InventoryInSheetPO> inventories;

	public AccountBookPO(String id, List<TransitPO> transit, List<PositionPO> position, List<StaffPO> staffs,
			List<CarPO> cars, List<AccountPO> accounts, List<InventoryInSheetPO> inventories) {
		super();
		this.date = id;
		this.transit = transit;
		this.staffs = staffs;
		this.cars = cars;
		this.accounts = accounts;
		this.inventories = inventories;
	}

	public AccountBookPO() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String id) {
		this.date = id;
	}

	public List<TransitPO> getTransit() {
		return transit;
	}

	public void setTransit(List<TransitPO> transit) {
		this.transit = transit;
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

	public List<InventoryInSheetPO> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryInSheetPO> inventories) {
		this.inventories = inventories;
	}
	
	
}
