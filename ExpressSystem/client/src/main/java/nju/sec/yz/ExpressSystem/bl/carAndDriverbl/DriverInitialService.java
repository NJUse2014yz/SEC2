package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.DriverPO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public interface DriverInitialService {
	/**
	 * 添加系统原始数据
	 */
	public ResultMessage init(List<DriverVO> driveers);
	
	/**
	 * po转vo
	 */
	public DriverVO show(DriverPO po);
	
	/**
	 * vo转po
	 */
	public DriverPO changeVOToPO(DriverVO vo);
}
