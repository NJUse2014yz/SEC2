package nju.sec.yz.ExpressSystem.bl.accountbl;

import nju.sec.yz.ExpressSystem.blservice.accountBlService.InitialBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.InitialVO;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * 处理界面期初建帐操作
 * @author 周聪
 */
public class InitialController implements InitialBlService{

	private Initial initial;
	
	public InitialController() {
		initial=new Initial();
	}
	
	@Override
	public ResultMessage start() {
		ResultMessage message=initial.start();
		return message;
	}
	
	@Override
	public ResultMessage addStaff(StaffVO vo) {
		ResultMessage message=initial.addStaff(vo);
		return message;
	}

	@Override
	public ResultMessage addTransit(TransitVO vo) {
		ResultMessage message=initial.addTransit(vo);
		return message;
	}

	@Override
	public ResultMessage addPosition(PositionVO vo) {
		ResultMessage message=initial.addPosition(vo);
		return message;
	}

	@Override
	public ResultMessage addCar(CarVO vo) {
		ResultMessage message=initial.addCar(vo);
		return message;
	}

	@Override
	public ResultMessage addAccount(AccountVO vo) {
		ResultMessage message=initial.addAccount(vo);
		return message;
	}

	@Override
	public ResultMessage addStock(InventoryInSheetVO vo) {
		ResultMessage message=initial.addStock(vo);
		return message;
	}

	@Override
	public ResultMessage finish() {
		ResultMessage message=initial.finish();
		return null;
	}

	@Override
	public InitialVO observeInitial() {
		
		return null;
	}

	

}
