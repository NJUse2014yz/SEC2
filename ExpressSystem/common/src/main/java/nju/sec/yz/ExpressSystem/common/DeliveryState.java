package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum DeliveryState  implements Serializable{
	GATHER,OFFICE_IN,OFFICE_OUT,STORE_IN,STORE_OUT,DELIVING,RECEICE;
}
