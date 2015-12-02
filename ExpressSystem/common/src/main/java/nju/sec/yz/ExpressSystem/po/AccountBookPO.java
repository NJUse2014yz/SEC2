package nju.sec.yz.ExpressSystem.po;

import java.io.Serializable;
import java.util.List;



/**
 * 
 * @author 周聪
 * 账目信息：机构、人员、车辆、库存、 银行账户信息（名称，余额）
 */
public class AccountBookPO implements Serializable{

	//每个机构一套账？
	
	private String id;
	
	//机构
	private TransitPO agency;
	
	//人员
	private List<StaffPO> staffs;
	
	//车辆
	private List<CarPO> cars;
	
	//银行账户
	private List<AccountPO> accounts;
	
	//TODO 库存
	private List<InventoryListPO> inventories;

	public AccountBookPO(TransitPO agency, List<StaffPO> staffs, List<CarPO> cars, List<AccountPO> accounts,
			List<InventoryListPO> inventories,String id) {
		super();
		this.agency = agency;
		this.staffs = staffs;
		this.cars = cars;
		this.accounts = accounts;
		this.inventories = inventories;
		this.id=id;
	}

	public TransitPO getAgency() {
		return agency;
	}

	public void setAgency(TransitPO agency) {
		this.agency = agency;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
}
