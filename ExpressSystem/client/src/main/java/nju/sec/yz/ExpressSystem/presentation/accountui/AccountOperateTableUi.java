package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;

public class AccountOperateTableUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
//	private AccountControler controler;
	private FinanceBlSevice financeBl;
	private BussinessVO bussinessvo;
	
	private DateChooser begin;
	private DateChooser end;
	private newTable tableIn;
	private newTable tableOut;
	private newJBut confirm;
	private newJBut excle;
	private JLabel warning;
	private Vector<String> nameIn=new Vector<String>();
	private Vector<String> nameOut=new Vector<String>();
	private Vector<Vector<String>> dataIn=new Vector<Vector<String>>();
	private Vector<Vector<String>> dataOut=new Vector<Vector<String>>();
	
	private static final int begin_x=225;
	private static final int begin_y=59;
	private static final int end_x=225;
	private static final int end_y=86;
	private static final int in_x=144;
	private static final int in_y=117;
	private static final int out_x=144;
	private static final int out_y=274;
	private static final int w=320;
	private static final int h=144;
	private static final int confirm_x=392;
	private static final int confirm_y=84;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int excle_x=350;
	private static final int excle_y=420;
	private static final int excle_w=110;
	private static final int excle_h=27;	
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
//	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
//	private ImageIcon excleIcon=new ImageIcon("graphic/account/button/excel_button.jpg");
	public AccountOperateTableUi(ClientControler mainControler,
			AccountButtonComponents bc) {
		super();
		this.mainControler=mainControler;
//		controler=mainControler.accountControler;
		this.bc=bc;
		financeBl=new FinanceController();
		nameIn.add("收款日期");
		nameIn.add("收款金额");
		nameIn.add("收款人");
		nameIn.add("快递条形码号");
		nameIn.add("营业厅编号");
		nameOut.add("付款日期 ");
		nameOut.add("付款金额");
		nameOut.add("付款人");
		nameOut.add("付款账号");
		nameOut.add("条目");
		nameOut.add("备注");
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		begin=new DateChooser(this,begin_x,begin_y);
		end=new DateChooser(this,end_x,end_y);
		
		tableIn=new newTable(dataIn,nameIn,this,false);
		tableIn.setBounds(in_x, in_y, w, h);
		tableIn.join();
		tableOut=new newTable(dataOut,nameOut,this,false);
		tableOut.setBounds(out_x, out_y, w, h);
		tableOut.join();
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x,confirm_y,confirm_w,confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				bussinessvo=financeBl.checkBusinessCircumstance(begin.getTime(), end.getTime());
				if(bussinessvo!=null){
					warning.setVisible(false);			
					changeData(bussinessvo);
					tableIn.resetData();
					tableOut.resetData();
				}
				else{
					warning.setText("日期选择有误，请重新选择");
					warning.setVisible(true);
				}
			}
		});
		add(confirm);
		
		excle=new newJBut("导出Excel");
		excle.setMargin(new java.awt.Insets(0,0,0,0));
		excle.setBounds(excle_x, excle_y, excle_w, excle_h);
		excle.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=financeBl.exportBussinessToExcel(bussinessvo);
				if(result.getResult()==Result.SUCCESS)
				{
					warning.setText("导出成功");
				}
				else
				{
					warning.setText(result.getMessage());
				}
				warning.setVisible(true);
				repaint();
			}
		});
		add(excle);
		
		setVisible(true);
	}
	private void changeData(BussinessVO bl)
	{
		dataIn.removeAllElements();
		dataOut.removeAllElements();
		List<PaymentSheetVO> invo=bl.in;
		List<OutVO> outvo=bl.out;
		for(int i=0;i<invo.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(invo.get(i).getPaymentInformation().getTime());
			vector.add(Double.toString(invo.get(i).getPaymentInformation().getAmount()));
			vector.add(invo.get(i).getPaymentInformation().getInDeliverId());
			vector.add(invo.get(i).getBarIds());
			vector.add(invo.get(i).getPaymentInformation().getPositionId());
			dataIn.add(vector);
		}
		for(int i=0;i<outvo.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(outvo.get(i).getOutInformation().getDate());
			vector.add(Double.toString(outvo.get(i).getOutInformation().getNum()));
			vector.add(outvo.get(i).getOutInformation().getPerson());
			vector.add(outvo.get(i).getOutInformation().getAccount());
			vector.add(outvo.get(i).getOutInformation().getReason());
			vector.add(outvo.get(i).getOutInformation().getComments());
			dataOut.add(vector);
		}		

	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/operate_table_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
