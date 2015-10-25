package nju.sec.yz.ExpressSystem.vo;

import java.util.List;



/**
 * 
 * @author 周聪 初期建账
 */
public class InitialVO {

	// 机构
	private String agency;

	// 人员
	private List<StaffVO> staffs;

	// 车辆
	private List<CarVO> cars;

	// 银行账户
	private List<AccountVO> accounts;

	// TODO 库存
	private List<InventoryVO> inventories;

	public InitialVO(String agency, List<StaffVO> staffs, List<CarVO> cars, List<AccountVO> accounts,
				List<InventoryVO> inventories) {
			
			this.agency = agency;
			this.staffs = staffs;
			this.cars = cars;
			this.accounts = accounts;
			this.inventories = inventories;
		}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public List<StaffVO> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<StaffVO> staffs) {
		this.staffs = staffs;
	}

	public List<CarVO> getCars() {
		return cars;
	}

	public void setCars(List<CarVO> cars) {
		this.cars = cars;
	}

	public List<AccountVO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountVO> accounts) {
		this.accounts = accounts;
	}

	public List<InventoryVO> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryVO> inventories) {
		this.inventories = inventories;
	}

}
