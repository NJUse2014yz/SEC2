package nju.sec.yz.ExpressSystem.common;

import java.io.Serializable;

public enum Status  implements Serializable{
	MANAGER("S"){
		
	},
	SENIOR_ACCOUNTANCY("E"){
		
	},
	JUNIOR_ACCOUNTANCY("e"){
		
	},
	TRANSIT("B"){
		
	},
	INVENTORY("A"){
		
	},
	POSITION("C"){
		
	},
	DELIVER("D"){
		
	},
	ADMINISTRATOR(""){
		
	};
	
	public static final int LENGTH_OF_NUMBER=3;//后几位的长度
	
	public String str;//生成id附加的字符
	
	private Status(String str){
		this.str=str;
	}
}
