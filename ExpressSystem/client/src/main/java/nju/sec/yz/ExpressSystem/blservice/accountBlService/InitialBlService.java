package nju.sec.yz.ExpressSystem.blservice.accountBlService;


import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 期初建账
 * @author 周聪
 *
 */
public interface InitialBlService {

	/**
	 * 添加初始员工
	 */
	public ResultMessage addStaff(StaffVO vo);
	
	/**
	 * 添加初始中转中心
	 */
	public ResultMessage addTransit(TransitVO vo);
	
	/**
	 * 添加初始营业厅
	 */
	public ResultMessage addPosition(PositionVO vo);
	
	/**
	 * 添加初始车辆
	 */
	public ResultMessage addCar(CarVO vo);
	
	/**
	 * 添加初始账户
	 */
	public ResultMessage addAccount(AccountVO vo);
	
	/**
	 * 添加初始库存
	 */
	public ResultMessage addStock(InventoryInSheetVO vo);
	
	/**
	 * 完成期初建帐
	 */
	public ResultMessage finish();
	
	/**
	 * 查看期初建帐
	 */
	public InitialVO observeInitial();
	
	
}
