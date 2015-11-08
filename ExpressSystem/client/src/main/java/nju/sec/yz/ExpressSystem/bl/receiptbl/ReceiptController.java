package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 负责实现单据业务所需要的服务
 * @author 周聪
 *
 */
public class ReceiptController implements ReceiptBlService{

	@Override
	public ArrayList<ReceiptVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptVO getSingle(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReceiptVO> getByType(ReceiptType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
