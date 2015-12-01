package nju.sec.yz.ExpressSystem.common;

/**
 * 表单类型
 * 
 * @author 周聪
 *
 */
public enum ReceiptType {
	COLLECTION() {

	}, // 收款单
	PAYMENT() {

	}, // 付款单
	DELIVER_RECEIPT() {

	}, // 寄件单
	POSITION_LOADING_RECEIPT() {

	},
	POSITION_RECEIVE_RECEIPT() {

	},
	POSITION_SEND_RECEIPT() {

	},
	TRANSIT_CAR_RECEIPT() {

	},
	TRANSIT_TRAIN_RECEIPT() {

	},
	TRANSIT_FLIGHT_RECEIPT() {

	},
	TRANSIT_RECEIVE_RECEIPT() {

	},
	TRANSIT_LOADING_RECEIPT() {

	},
	INVENTORY_IN() {

	},
	INVENTORY_OUT() {

	};

	

	

}
