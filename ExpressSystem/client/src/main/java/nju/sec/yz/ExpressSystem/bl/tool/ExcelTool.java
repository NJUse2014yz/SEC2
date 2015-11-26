package nju.sec.yz.ExpressSystem.bl.tool;

import java.awt.Label;
import java.io.File;

/**
 * 导出excel的工具类
 * @author 周聪
 *
 */
public class ExcelTool {
	public void exportExcel(String filename){
		File file=new File("c:");
		try{
			file.createNewFile();
			//创建工作簿
			WritableWorkbook workbook=Workbook.creatWorkbook(file);
			//创建sheet
			WritableSheet sheet=workbook.creatSheet("sheet1",0);
			//4,创建表头单元格
			//表头数组
			String[] title={"id","name","sex"};
			Label lable=null;
			//将表头添加到第一行
			for(int i=0;i<title.length;i++){
				lable=new Label(i,0,title[i]);
				sheet.addCell(lable);
			}

			//5,循环添加数据,(注意数据下标)
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
	}
}
