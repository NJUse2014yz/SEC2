package nju.sec.yz.ExpressSystem.bl.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	
	public static void main(String[] args) {
		System.out.println(getTime());
	}
	
}
