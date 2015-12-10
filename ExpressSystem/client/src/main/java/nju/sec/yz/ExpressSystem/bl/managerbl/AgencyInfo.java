package nju.sec.yz.ExpressSystem.bl.managerbl;

public interface AgencyInfo {

	/**
	 * 获得中转中心所在地
	 * @param name
	 */
	public String getTrancitLocation(String name);
	
	/**
	 * 通过名字查id
	 */
	public String getName(String id);
	
	/**
	 * 通过id查名字
	 */
	public String getId(String name);
	
}
