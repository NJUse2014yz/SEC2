package nju.sec.yz.ExpressSystem.bl.managerbl;


public interface PriceService {
	/**
	 * 快递价格
	 */
	public double getDeliverPrice();
	
	/**
	 * 汽车运输单价
	 */
	public double getCarPrice();
	
	/**
	 * 火车运输单价
	 */
	public double getTrainPrice();
	
	/**
	 * 飞机运输单价
	 */
	public double getPlanePrice();
	
}
