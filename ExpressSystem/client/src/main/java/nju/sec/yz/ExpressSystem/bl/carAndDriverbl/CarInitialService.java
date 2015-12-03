package nju.sec.yz.ExpressSystem.bl.carAndDriverbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.CarPO;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public interface CarInitialService {

	/**
	 * 添加系统原始数据
	 */
	public ResultMessage init(List<CarVO> cars);
	
	
	/**
	 * po转vo
	 */
	public CarVO show(CarPO po);
	
	/**
	 * vo转po
	 */
	public CarPO changeVOToPO(CarVO vo);
	
}
