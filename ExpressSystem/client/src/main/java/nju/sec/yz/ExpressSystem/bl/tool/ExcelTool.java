package nju.sec.yz.ExpressSystem.bl.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;

/**
 * 导出excel的工具类
 * @author 周聪
 * @implement sai
 */
public class ExcelTool {
	public static ResultMessage exportExcel(String filename,String txt){
		ResultMessage message=new ResultMessage(Result.FAIL,"fail");
		if(txt.equals("")||txt.isEmpty()){
			message.setMessage("呜呜，导出失败");
			return message;
		}
		File po=new File(txt);
		File file=new File(filename);			
	
		try{
			file.createNewFile();
			//创建工作簿
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//创建sheet
			WritableSheet sheet=workbook.createSheet("sheet1",0);
			
			//读取表头内容
			BufferedReader reader=new BufferedReader(new FileReader(po));
			//创建表头单元格
			//表头数组
			String titleLine=reader.readLine();
			if(titleLine.equals("")||titleLine==null){
				message.setMessage("呜呜，导出失败");
				reader.close();
				return message;
			}
			String[] title=titleLine.split(" ");
			Label lable=null;
			
			//将表头添加到第一行
			for(int i=0;i<title.length;i++){
				lable=new Label(i,0,title[i]);
				sheet.addCell(lable);
			}
			String contentline=null;
			//循环添加数据,(注意数据下标)
			int k=1;
			while((contentline=reader.readLine())!=null){
				String[] content=contentline.split(" ");
				for(int j=0;j<content.length;j++){
					lable=new Label(j,k,content[j]);
					sheet.addCell(lable);
				}
				k++;
			}
			//写入数据
			workbook.write();
			//关闭
			reader.close();
			workbook.close();
		}catch(Exception  e){
			e.printStackTrace();
			message.setMessage("呜呜，导出失败");
			return message;
		}
		message.setResult(Result.SUCCESS);
		return message;
	}
	public static void main(String[] args) {
		ExcelTool.exportExcel("xsl/"+"1.xls", "File/test.txt");
	}
}
