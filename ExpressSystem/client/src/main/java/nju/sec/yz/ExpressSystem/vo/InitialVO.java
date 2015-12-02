package nju.sec.yz.ExpressSystem.vo;

import java.util.List;
/**
 * 
 * @author 周聪 初期建账
 */
public class InitialVO {
	
	public String id;

	// 机构
	public List<TransitVO> agency;

	// 人员
	public List<StaffVO> staffs;

	// 车辆
	public List<CarVO> cars;

	// 银行账户
	public List<AccountVO> accounts;

	// 库存
	public List<InventoryInSheetVO> inventories;

}
