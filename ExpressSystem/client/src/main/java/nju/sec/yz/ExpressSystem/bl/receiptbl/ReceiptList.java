package nju.sec.yz.ExpressSystem.bl.receiptbl;

import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.ReceiptVO;

/**
 * 表单列表的领域模型对象
 * 负责存放未被审批的单据，并且控制和外部交互
 * @author 周聪
 */
public class ReceiptList {
	
	//TODO 单据审批的流程
	
	
	
	
	
	public ArrayList<ReceiptVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ReceiptVO getSingle(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage approve(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage deny(ReceiptVO vo, String reason) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<ReceiptVO> getByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage modify(ReceiptVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
