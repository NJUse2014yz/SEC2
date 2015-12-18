package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.AccountController;
import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;

public class ManagerAccountCheck extends JPanel {

	// 账户信息
	private AccountBlService account = new AccountController();
	// 经营情况表和成本收益表
	private FinanceBlSevice finance = new FinanceController();

	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private JComboBox choice;

	private JLabel begin;
	private JLabel end;
	
	private newTable tableI;
	private newTable tableO;
	private newTable tableA;
	private newTable tableC;

	private DateChooser date1;
	private DateChooser date2;
	private JButton confirm;

	private JLabel warning;
	
	private Vector<Vector<String>> dataI=new Vector<Vector<String>>();
	private Vector<String> nameI=new Vector<String>();
	private Vector<Vector<String>> dataO=new Vector<Vector<String>>();
	private Vector<String> nameO=new Vector<String>();
	private Vector<Vector<String>> dataA=new Vector<Vector<String>>();
	private Vector<String> nameA=new Vector<String>();
	private Vector<Vector<String>> dataC=new Vector<Vector<String>>();
	private Vector<String> nameC=new Vector<String>();
	
	private static final int in_x=144;
	private static final int in_y=117;
	private static final int out_x=144;
	private static final int out_y=274;
	private static final int w=320;
	private static final int h=144;
	
	ImageIcon confirmIcon = new ImageIcon("graphic/account/button/confirm_button.jpg");

	public ManagerAccountCheck(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		iniManagerAccountCheck();
	}

	private void iniManagerAccountCheck() {
		mbc.changePanel(this);
		mbc.change();

		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		nameI.add("收款日期");
		nameI.add("收款金额");
		nameI.add("收款人");
		nameI.add("快递条形码号");
		nameO.add("付款日期 ");
		nameO.add("付款金额");
		nameO.add("付款人");
		nameO.add("付款账号");
		nameO.add("条目");
		nameO.add("备注");
		nameA.add("账号");
		nameA.add("余额");
		nameC.add("总收入");
		nameC.add("总支出");
		nameC.add("总利润");
		
		begin=new JLabel();
		begin.setText("起始时间");
		begin.setBounds(139,92,100,30);
		begin.setFont(new Font("Dialog", 1, 15));
		begin.setForeground(Color.white);
		begin.setVisible(false);
		add(begin);
		
		end=new JLabel();
		end.setText("结束时间");
		end.setBounds(139,116,100,30);
		end.setFont(new Font("Dialog", 1, 15));
		end.setForeground(Color.white);
		end.setVisible(false);
		add(end);

		tableI=new newTable(dataI,nameI,this,false);
		tableI.setBounds(in_x,in_y,w,h);
		tableI.join();
		tableI.setVisible(false);
		
		tableO=new newTable(dataO,nameO,this,false);
		tableO.setBounds(out_x,out_y,w,h);
		tableO.join();
		tableO.setVisible(false);
		
		tableA=new newTable(dataA,nameA,this,false);
		tableA.setBounds(in_x,in_y,2*w,h);
		tableA.join();
		tableA.setVisible(false);
		
		tableC=new newTable(dataC,nameC,this,false);
		tableC.setBounds(in_x,in_y,2*w,h);
		tableC.join();
		tableC.setVisible(false);
		
		warning= new JLabel();
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
		
		choice = new JComboBox(new String[]{"账户信息","经营情况表","成本收益表"});
		choice.setBounds(244, 62, 80, 21);
		add(choice);
		choice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("经营情况表")) {
					iniOperate();
				} else {
					if (choice.getSelectedItem().equals("账户信息")) {
						iniAccount();
					} else {
						iniCost();
					}
				}
			}
		});
		
		confirm = new JButton(confirmIcon);
		confirm.setBounds(392, 104, 72, 24);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				BussinessVO bvo = finance.checkBusinessCircumstance(date1.getTime(), date2.getTime());
				
				if (bvo != null) {
					warning.setVisible(false);
					changeData(bvo);
					tableI.resetData();
					tableO.resetData();
				} else {
					warning.setText("日期选择有误，请重新选择");
					warning.setVisible(true);
				}
			}
		});
		add(confirm);
	}

	private void iniCost() {
		removeAll();
		tableI.setVisible(false);
		tableO.setVisible(false);
		tableA.setVisible(false);
		tableC.setVisible(true);
		begin.setVisible(false);
		end.setVisible(false);
		mbc.add();
		add(choice);
		dataC.removeAllElements();
		ProfitVO prvo = finance.makeCostReceipt();
		Vector<String> vector=new Vector<String>();
		vector.add(Double.toString(prvo.in));
		vector.add(Double.toString(prvo.out));
		vector.add(Double.toString(prvo.profit));
		dataC.add(vector);
		tableC.resetData();
		repaint();
	}

	private void iniOperate() {
		removeAll();
		tableI.setVisible(true);
		tableO.setVisible(true);
		tableA.setVisible(false);
		tableC.setVisible(false);
		begin.setVisible(true);
		end.setVisible(true);
		mbc.add();
		add(choice);
		add(confirm);
		date1 = new DateChooser(this, 210, 88);
		date2 = new DateChooser(this, 210, 110);
		repaint();
	}

	private void iniAccount() {
		removeAll();
		mbc.add();
		add(choice);
		dataA.removeAllElements();
		tableI.setVisible(false);
		tableO.setVisible(false);
		tableA.setVisible(true);
		tableC.setVisible(false);
		begin.setVisible(false);
		end.setVisible(false);
		ArrayList<AccountVO> accountlist = account.observeList();
		for (int i = 0; i < accountlist.size(); i++) {
			Vector<String> vector=new Vector<String>();
			vector.add(accountlist.get(i).getName());
			vector.add(Double.toString(accountlist.get(i).getBalance()));
			dataA.add(vector);
		}
		tableA.resetData();
		repaint();
	}
	
	private void changeData(BussinessVO bvo)
	{
		ArrayList<PaymentSheetVO> in = (ArrayList<PaymentSheetVO>) bvo.in;
		ArrayList<OutVO> out = (ArrayList<OutVO>) bvo.out;
		for (int i = 0; i < in.size(); i++) {
			Vector<String> vector=new Vector<String>();
			vector.add(in.get(i).getPaymentInformation().getTime());
			vector.add(Double.toString(in.get(i).getPaymentInformation().getAmount()));
			vector.add(in.get(i).getPaymentInformation().getInDeliverId());
			vector.add(in.get(i).getBarIds());
			dataI.add(vector);
		}
		for (int i = 0; i < out.size(); i++) {
			Vector<String> vector=new Vector<String>();
			vector.add(out.get(i).getOutInformation().getDate());
			vector.add(Double.toString(out.get(i).getOutInformation().getNum()));
			vector.add(out.get(i).getOutInformation().getPerson());
			vector.add(out.get(i).getOutInformation().getAccount());
			vector.add(out.get(i).getOutInformation().getReason());
			vector.add(out.get(i).getOutInformation().getComments());
			dataO.add(vector);
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background19.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
