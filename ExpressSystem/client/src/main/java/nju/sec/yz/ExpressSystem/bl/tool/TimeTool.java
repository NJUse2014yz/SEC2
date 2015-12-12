package nju.sec.yz.ExpressSystem.bl.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nju.sec.yz.ExpressSystem.bl.deliverbl.ValidHelper;

/**
 * 获得时间
 * @author 周聪
 *
 */
public class TimeTool {

	/**
	 * 日期格式规则：8位数字，如20151007
	 * @return
	 */
	public static String getDate(){
		String date;
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        date = sdf.format(d);
        return date;
	}
	
	public static String getTime(){
		String date;
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
        date = sdf.format(d);
        return date;
	}
	
	/**
	 * @return 日期月份
	 */
	public static String getMonth(String date){
		if(!ValidHelper.isValidDate(date))
			return null;
		String month=date.substring(4, 6);
		
		if(month.startsWith("0"))
			month=month.substring(1);
		
		return month;
	}
	
	/**
	 * @param date 日期（八位字符串）
	 * @return 
	 */
	public static String getDay(String date){
		if(!ValidHelper.isValidDate(date))
			return null;
		String day=date.substring(6, 8);
		
		if(day.startsWith("0"))
			day=day.substring(1);
		return day;
	}

	
}
