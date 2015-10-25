package nju.sec.yz.ExpressSystem.bl.stub;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.receiptBlService.ReceiptBlService;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

public class ReceiptBlStub implements ReceiptBlService {

	@Override
	public ArrayList<ReceiptVO> getAll() {
		// TODO 自动生成的方法存根
		return new ArrayList<ReceiptVO>();
	}

	@Override
	public ReceiptVO getSingle(int i) {
		// TODO 自动生成的方法存根
		return new ReceiptVO();
	}

	@Override
	public ResultMessage approve(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deny(ReceiptVO vo, String reason) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<ReceiptVO> getByType(String type) {
		// TODO 自动生成的方法存根
		return new ArrayList<ReceiptVO>();
	}

	@Override
	public ResultMessage modify(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		return ResultMessage.SUCCESS;
	}

}
