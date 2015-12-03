package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

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
	public ResultMessage addStaff(List<StaffVO> vo) {
		ResultMessage message=initial.addStaff(vo);
		return message;
	}

	@Override
	public ResultMessage addTransit(List<TransitVO> vo) {
		ResultMessage message=initial.addTransit(vo);
		return message;
	}

	@Override
	public ResultMessage addPosition(List<PositionVO> vo) {
		ResultMessage message=initial.addPosition(vo);
		return message;
	}

	@Override
	public ResultMessage addCar(List<CarVO> vo) {
		ResultMessage message=initial.addCar(vo);
		return message;
	}

	@Override
	public ResultMessage addAccount(List<AccountVO> vo) {
		ResultMessage message=initial.addAccount(vo);
		return message;
	}

	@Override
	public ResultMessage addStock(List<InventoryInSheetVO> vo) {
		ResultMessage message=initial.addStock(vo);
		return message;
	}

	@Override
	public ResultMessage finish() {
		ResultMessage message=initial.finish();
		return message;
	}

	@Override
	public InitialVO observeInitial() {
		
		return null;
	}

	

}
