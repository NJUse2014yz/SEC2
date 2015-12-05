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
		ReceiptList list=new ReceiptList();
		return list.getAll();
	}

	@Override
	public ReceiptVO getSingle(String id) {
		ReceiptList list=new ReceiptList();
		return list.getSingle(id);
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		ReceiptList list=new ReceiptList();
		return list.approve(vo);
	}

	@Override
	public ArrayList<ReceiptVO> getByType(ReceiptType type) {
		ReceiptList list=new ReceiptList();
		return list.getByType(type);
	}

	@Override
	public ResultMessage modify(ReceiptVO vo) {
		ReceiptList list=new ReceiptList();
		return list.modify(vo);
	}

}
