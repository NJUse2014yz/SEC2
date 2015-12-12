package nju.sec.yz.ExpressSystem.common;

/**
 * 表单类型
 * 
 * @author 周聪
 *
 */
public enum ReceiptType {
	COLLECTION() {

		@Override
		public String toString() {
			return "收款单";
		}

	}, // 收款单
	PAYMENT() {

		@Override
		public String toString() {
			
			return "付款单";
		}

	}, // 付款单
	DELIVER_RECEIPT() {

		@Override
		public String toString() {
			
			return "寄件单";
		}

	}, // 寄件单
	POSITION_LOADING_RECEIPT() {

		@Override
		public String toString() {
			
			return "营业厅装车单";
		}

	},
	POSITION_RECEIVE_RECEIPT() {

		@Override
		public String toString() {
			
			return "营业厅到达单";
		}

	},
	POSITION_SEND_RECEIPT() {

		@Override
		public String toString() {
			
			return "派件单";
		}

	},
	TRANSIT_CAR_RECEIPT() {

		@Override
		public String toString() {
			
			return "中转单";
		}

	},
	TRANSIT_TRAIN_RECEIPT() {

		@Override
		public String toString() {
			
			return "中转单";
		}

	},
	TRANSIT_FLIGHT_RECEIPT() {

		@Override
		public String toString() {
			
			return "中转单";
		}

	},
	TRANSIT_RECEIVE_RECEIPT() {

		@Override
		public String toString() {
			
			return "中转中心到达单";
		}

	},
	TRANSIT_LOADING_RECEIPT() {

		@Override
		public String toString() {
			
			return "中转中心装车单";
		}

	},
	INVENTORY_IN() {

		@Override
		public String toString() {
			
			return "入库单";
		}

	},
	INVENTORY_OUT() {

		@Override
		public String toString() {
			
			return "";
		}

	};

	
	@Override
	public abstract String toString();

}
