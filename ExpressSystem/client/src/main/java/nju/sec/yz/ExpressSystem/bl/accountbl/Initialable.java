package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;


/**
 * 所有需要期初建账的类的通用接口
 * Car,Inventory,Transit,Staff,Account
 * VO 所有vo的泛指
 * PO 所有po的泛指
 * @author 周聪
 */
public interface Initialable <VO,PO>{
	/**
	 * 添加系统原始数据
	 */
	public ResultMessage init(List<VO> vos);
	
	/**
	 * po转vo
	 */
	public VO show(PO po);
	
	/**
	 * vo转po
	 */
	public PO changeVOToPO(VO vo);
}
