package nju.sec.yz.ExpressSystem.vo;

import java.util.List;
/**
 * 
 * @author 周聪 初期建账
 */
public class InitialVO {
	
	private String id;

	// 机构
	private AgencyVO agency;

	// 人员
	private List<StaffVO> staffs;

	// 车辆
	private List<CarVO> cars;

	// 银行账户
	private List<AccountVO> accounts;

	// TODO 库存
	private List<InventoryListVO> inventories;

	public InitialVO(AgencyVO agency, List<StaffVO> staffs, List<CarVO> cars, List<AccountVO> accounts,
				List<InventoryListVO> inventories) {
			
			this.agency = agency;
			this.staffs = staffs;
			this.cars = cars;
			this.accounts = accounts;
			this.inventories = inventories;
		}

	public AgencyVO getAgency() {
		return agency;
	}

	public void setAgency(AgencyVO agency) {
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

	public List<InventoryListVO> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryListVO> inventories) {
		this.inventories = inventories;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
