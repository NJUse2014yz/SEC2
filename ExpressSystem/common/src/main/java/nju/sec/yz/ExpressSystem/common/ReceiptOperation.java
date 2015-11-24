package nju.sec.yz.ExpressSystem.common;

public enum ReceiptOperation {

	APPROVE(){
		@Override
		public String toString(){
			return "审批通过";
		}
		
	},MODIFY(){
		@Override
		public String toString(){
			return "修改";
		}
	};
	
	/*public static void main(String[] args) {
		System.out.println(APPROVE);
	}*/
	
}
