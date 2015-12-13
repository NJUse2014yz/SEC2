package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

/**
 * 
 * @author YU Fan
 *
 */
public enum DeliveryState  implements Serializable{
	SUBMIT,//单据处于待审批状态
	GATHER,
	OFFICE_IN,
	OFFICE_OUT,
	TRANSIT_IN,
	INVENTORY_IN,
	INVENTORY_OUT,
	TRANSIT_OUT,
	DELIVING,//派件
	RECEICE;
}
