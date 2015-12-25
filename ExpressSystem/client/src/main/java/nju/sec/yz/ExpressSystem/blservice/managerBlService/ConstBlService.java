package nju.sec.yz.ExpressSystem.blservice.managerBlService;

import java.util.List;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;


/**
 * @author xiaosaisai
 * 常量制定--改和查
 */
public interface ConstBlService {
	/**
	 * 修改城市常量
	 * @param cv
	 * @return
	 */
	public ResultMessage modifyCity(CityVO cv);

	/**
	 * 根据出发地和到达地获得单个城市常量
	 */
	public CityVO observeCity(String beginPlace,String endPlace) ;
	
	/**
	 * 查看所有城市常量
	 * @return
	 */
	public List<CityVO> observeAllCity();
	
	/**
	 * 添加城市常量
	 */
	public ResultMessage addCity(CityVO cp);
	
	/**
	 * 删除城市常量
	 */
	public ResultMessage deleteCity(String beginPlace,String endPlace);
	
	
	
	/**
	 * 修改价格常量
	 */
	public ResultMessage modifyPrice(PriceVO pp);
	
	/**
	 * 获得价格常量
	 * @return
	 */
	public PriceVO observePrize();
}
