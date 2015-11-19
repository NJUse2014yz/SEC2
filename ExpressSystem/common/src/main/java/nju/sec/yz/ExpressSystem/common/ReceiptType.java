package nju.sec.yz.ExpressSystem.common;
/**
 * 表单类型
 * @author 周聪
 *
 */
public enum ReceiptType {
	COLLECTION("k",3){
		
	},//收款单
	PAYMENT("f",3){
		
	},//付款单
	DELIVER_RECEIPT("j",5){
		
	},//寄件单
	POSITION_LOADING_RECEIPT("yz",3){
		
	},
	POSITION_RECEIVE_RECEIPT("yd",5){
		
	},
	POSITION_SEND_RECEIPT("p",3){
		
	},
	TRANSIT_RECEIPT("z",5){
		
	},
	TRANSIT_RECEIVE_RECEIPT("zd",5){
		
	},
	TRANSIT_LOADING_RECEIPT("zz",3){
		
	},
	INVENTORY_IN("r",5){
		
	},
	INVENTORY_OUT("c",5){
		
	};
	
	private ReceiptType(String idStr,int length){
		this.idStr=idStr;
		this.length=length;
	}
	
	/**
	 * 生成表单id时要加的字符
	 */
	private String idStr;
	
	/**
	 * 表单的后几位编号的长度
	 */
	private int length;
	
	public String getIdStr() {
		return idStr;
	}

	public int getLength() {
		return length;
	}

	
	

}
