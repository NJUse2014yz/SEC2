package nju.sec.yz.ExpressSystem.bl.tool;

import java.io.File;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;

/**
 * 导出excel的工具类
 * @author 周聪
 *
 */
public class ExcelTool {
	public static ResultMessage exportExcel(String filename,String txt){
		ResultMessage message=new ResultMessage(Result.FAIL);
		if(filename.equals("")||filename.isEmpty()){
			message.setMessage("亲，你得取一个文件名哈");
			return message;
		}
			
		File file=new File("xsl/"+filename+".xls");
		try{
			file.createNewFile();
			//创建工作簿
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//创建sheet
			WritableSheet sheet=workbook.createSheet("sheet1",0);
			//创建表头单元格
			//表头数组
			String[] title={"id","name","sex"};
			Label lable=null;
			//将表头添加到第一行
			for(int i=0;i<title.length;i++){
				lable=new Label(i,0,title[i]);
				sheet.addCell(lable);
			}

			//循环添加数据,(注意数据下标)
			for(int i=0;i<10;i++){
				lable=new Label(0,i,"a"+1);
				sheet.addCell(lable);
				lable=new Label(1,i,"uesr"+i);
				sheet.addCell(lable);
				lable=new Label(2,i,"男"+1);
				sheet.addCell(lable);
			}
			//写入数据
			workbook.write();
			workbook.close();
		}catch(Exception  e){
			e.printStackTrace();
		}
		return null;
	}
//	public static void main(String[] args) {
//		ExcelTool.exportExcel("xsl/"+".xls");
//	}
}
