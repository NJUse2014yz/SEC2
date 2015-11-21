package nju.sec.yz.ExpressSystem.bl.deliverbl;

import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;

public class ValidHelper {
	
	/**
	 * 是否符合日期的格式
	 */
	public static boolean isValidDate(String date){
		if(!isNumber(date))
			return false;
		if(date.length()!=8)
			return false;
		
		int dateToInt=Integer.parseInt(date);
		
		String now=TimeTool.getDate();
		
		int nowToInt=Integer.parseInt(now);
		
		//超过今天
		if(dateToInt>nowToInt)
			return false;
		
		
		return true;
	}
	
	
	/**
	 * 是否为数字
	 */
	public static boolean isNumber(String str){
		if(str==null||str.length()==0)
			return false;
		char[] numbers=str.toCharArray();
		for(int i=0;i<numbers.length;i++)
			if('0'>numbers[i]||numbers[i]>'9')
				return false;
		return true;
	}
	/**
	 * 是否符合手机号码的格式
	 */
	public static boolean isCellphone(String str){
		if(str==null||str.length()==0)
			return false;
		if(str.length()!=11)
			return false;
		return isNumber(str);
	}
	/**
	 * 是否符合订单条形码号的格式
	 */
	public static boolean isBarId(String str){
		if(str==null||str.length()==0)
			return false;
		if(str.length()!=10)
			return false;
		return isNumber(str);
	}
	/**
	 * 是否为合理的数字
	 */
	public static boolean isTotal(String str){
		if(str==null||str.length()==0)
			return false;
		if(!isNumber(str))
			return false;
		int n= Integer.parseInt(str);
		if(n<0||n>65536)
			return false;
		return true;
	}
}