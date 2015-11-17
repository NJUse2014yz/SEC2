package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum DeliveryState  implements Serializable{
	GATHER,
	BROKEN,//损坏
	OFFICE_IN,
	OFFICE_OUT,
	STORE_IN,
	STORE_OUT,
	DELIVING,//派件
	RECEICE;
}
