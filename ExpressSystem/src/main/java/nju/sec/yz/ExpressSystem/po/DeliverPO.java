package nju.sec.yz.ExpressSystem.po;

import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;

/**
 * 
 * @author YU Fan
 *
 */
public class DeliverPO {
	private String barId;
	
	private ToAndFromInformation toPerson;
	private ToAndFromInformation fromPerson;
	private GoodInformation good;
	private DeliveryType deliveryType;
	private PackType packType;
	private double costForPack;//?
	private double costForAll;
	private String predictTime;
	
}
