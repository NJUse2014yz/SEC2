package nju.sec.yz.ExpressSystem.common;

public enum ReceiptOperation {

	APPROVE(){
		@Override
		public String toString(){
			return "通过审批";
		}
		
	},MODIFY(){
		@Override
		public String toString(){
			return "被修改";
		}
	};
	
	
	
}
