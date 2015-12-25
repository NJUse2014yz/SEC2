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
	 * 
	 * @param cv
	 * @return
	 */
	public ResultMessage modifyCity(CityVO cv);

	/**
	 * 
	 */
	public CityVO observeCity(String beginPlace,String endPlace) ;
	
	/**
	 * 
	 * @return
	 */
	public List<CityVO> observeAllCity();
	
	/**
	 * 
	 */
	public ResultMessage addCity(CityVO cp);
	
	/**
	 * 
	 */
	public ResultMessage deleteCity(String beginPlace,String endPlace);
	
	/**
	 * 获得所有城市名称
	 */
	public List<String> getCities();
	
	/**
	 * 
	 */
	public ResultMessage modifyPrice(PriceVO pp);
	
	/**
	 * 
	 * @return
	 */
	public PriceVO observePrize();
}
