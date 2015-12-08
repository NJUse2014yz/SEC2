package nju.sec.yz.ExpressSystem.po;
/**
 * @author YU Fan
 */
import java.io.Serializable;

import nju.sec.yz.ExpressSystem.common.PriceInformation;

public class PricePO implements Serializable{
	private PriceInformation priceInformation;
	
	public PricePO(){
		priceInformation=new PriceInformation();
	}

	public PriceInformation getPriceInformation() {
		return priceInformation;
	}

	public void setPriceInformation(PriceInformation priceInformation) {
		this.priceInformation = priceInformation;
	}

}
