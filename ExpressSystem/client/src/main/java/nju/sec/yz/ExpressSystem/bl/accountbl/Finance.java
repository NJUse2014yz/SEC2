package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import nju.sec.yz.ExpressSystem.bl.tool.ExcelTool;
import nju.sec.yz.ExpressSystem.bl.tool.TimeTool;
import nju.sec.yz.ExpressSystem.common.OutInformation;
import nju.sec.yz.ExpressSystem.common.PaymentInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;

/**
 * 负责财务情况的具体实现
 * 持有付款单和收款单
 * @author 周聪
 */
public class Finance {
	
	
	/**
	 * 经营情况表
	 * 可以选择开始日期和结束日期
	 * 显示期间内所有的入款单和收款单信息
	 * @param begin 开始日期
	 * @param end	结束日期
	 * @return
	 */
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		BussinessVO vo=new BussinessVO();
		
		Collection in=new Collection();
		vo.in=in.getByTime(begin, end);
		
		Payment out=new Payment();
		vo.out=out.getByTime(begin, end);
		
		return vo;
	}
	
	
	/**
	 * 生成截止当前日期的成本收益表
	 *（总收入、总支出、总利润=总收入-总支出）
	 * @return
	 */
	public ProfitVO makeCostReceipt() {
		Collection in=new Collection();
		double colletion=in.getAllCollection();
		
		Payment out=new Payment();
		double cost=out.getAllPayment();
		
		ProfitVO vo=new ProfitVO(colletion, cost, colletion-cost);
		
		
		return vo;
	}
	
	/**
	 * 导出成本收益表excel
	 * @param rv
	 */
	public ResultMessage exportCostToExcel(ProfitVO rv) {
		ResultMessage message = new ResultMessage(Result.SUCCESS);
		String filename = getPVFileName();
		String txt = getPVTxtPath(rv);
		message=ExcelTool.exportExcel(filename, txt);
		return message;
	}
	
	

	private String getPVFileName() {
		String result = "";
		int count = Integer.parseInt(getCurrentCounter("File/cost_count_excel")) + 1;
		// System.out.println(count);
		saveCounter(TimeTool.getDate() + count,"File/cost_count_excel");
		result = "xsl/" + TimeTool.getDate() + "成本收益表" + count + ".xls";
		return result;
	}
	

	private String getPVTxtPath(ProfitVO pv) {
		int count = Integer.parseInt(getCurrentCounter("File/cost_count_excel")) + 1;
		String[] title = { "总收入 ", "总支出 ", "总利润 "};
		File file = new File("File/costFile/" + TimeTool.getDate() + "ProfitSheet" + count);
		try {
			FileWriter fw = new FileWriter(file);
			for (String str : title) {
				fw.write(str);
			}
			fw.write("\n");
			fw.write(pv.in+" "+pv.out+" "+pv.profit);
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(file.getPath());
		return file.getPath();
	}
	

	private String getCurrentCounter(String filename) {
		File file = new File(filename);
		String str = "";
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			str = (String) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (str.length() < 9) {
			System.out.println("---");
			return 0 + "";
		}
		if (!str.substring(0, 8).equals(TimeTool.getDate())) {
			// saveCounter(TimeTool.getDate()+0);
			return 0 + "";
		}
		return str.charAt(8) + "";
	}

	/**
	 * 保存当天文件日期和计数的次数
	 * @param fileName 
	 */
	private void saveCounter(String string, String fileName) {
		File file = new File(fileName);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(string);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 导出经营情况表
	 */
	public ResultMessage exportBussinessToExcel(BussinessVO vo){
		ResultMessage message = new ResultMessage(Result.SUCCESS);
		String filename = getBVFileName();
		String txt = getBVTxtPath(vo);
		message=ExcelTool.exportExcel(filename, txt);
		return message;
	}


	private String getBVFileName() {
		String result = "";
		int count = Integer.parseInt(getCurrentCounter("File/bussiness_count_excel")) + 1;
		// System.out.println(count);
		saveCounter(TimeTool.getDate()+count, "File/bussiness_count_excel");
		result = "xsl/" + TimeTool.getDate() + "经营情况表" + count + ".xls";
		return result;
	}
	
	private String getBVTxtPath(BussinessVO bv) {
		List<PaymentSheetVO> paylist = bv.in;
		List<OutVO> outlist=bv.out;
		int count = Integer.parseInt(getCurrentCounter("bussiness_count_excel")) + 1;
		String[] title1 = {"收款日期 ","收款金额 ","收款人 ","快递条形码号 ","营业厅编号"};
		String[] title2 = {"付款日期 ","付款金额 ","付款人 ","付款账号 ","条目 ","备注"};
		File file = new File("File/bussinessFile/" + TimeTool.getDate() + "BussinessSheet" + count);
		try {
			FileWriter fw = new FileWriter(file);
			for (String str : title1) {
				fw.write(str);
			}
			for (int i = 0; i < paylist.size(); i++) {
				fw.write("\n");
				PaymentSheetVO invo = paylist.get(i);
				PaymentInformation ii = invo.getPaymentInformation();
				fw.write(ii.getTime() + " " + ii.getAmount() + " " + ii.getInDeliverId()
						+ " " +invo.getId()+" "+ii.getPositionId());
			}
			for (String str : title2) {
				fw.write(str);
			}
			for (int i = 0; i < outlist.size(); i++) {
				fw.write("\n");
				OutVO outvo = outlist.get(i);
				OutInformation oi = outvo.getOutInformation();
				fw.write(oi.getDate() + " " + oi.getNum() + " " + oi.getPerson()+" "+oi.getAccount()
						+ " " +oi.getReason()+" "+oi.getComments());
			}
			fw.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// System.out.println(file.getPath());
		return file.getPath();
	}
}
