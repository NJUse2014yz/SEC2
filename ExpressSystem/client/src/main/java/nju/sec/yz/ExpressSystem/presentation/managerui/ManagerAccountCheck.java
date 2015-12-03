package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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

	private JTable table;
	private JScrollPane jsc;
	private TableModel model;

	private Object[][] TableData;
	private String[] title;

	private JTable inTable;
	private JTable outTable;
	private JScrollPane inScroll;
	private JScrollPane outScroll;
	private DateChooser date1;
	private DateChooser date2;
	private JButton confirm;

	private JLabel warning = new JLabel();

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

		String[] choices = { "账户信息", "经营情况表", "成本收益表" };
		choice = new JComboBox(choices);
		choice.setBounds(244, 62, 80, 21);
		add(choice);

		choice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choice.getSelectedItem().equals("经营情况表")) {
					remove(jsc);
					iniOperate();
				} else {
					if (choice.getSelectedItem().equals("账户信息")) {
						iniAccount();
					} else {
						// (choice.getSelectedItem().equals("成本收益表")) {
						iniCost();
					}
					removeAll();
					model = new DefaultTableModel(TableData, title);
					table.setModel(model);
					// table.repaint();
					add(jsc);
					add(choice);
					mbc.add();
					repaint();
				}
			}

		});

		TableData = null;
		title = null;

		model = new DefaultTableModel(TableData, title);
		table = new JTable(model);
		jsc = new JScrollPane(table);
		jsc.setBounds(142, 91, 317, 199);
		add(jsc);
		jsc.setVisible(true);

	}

	private void iniCost() {
		// TODO Auto-generated method stub
		ProfitVO prvo = finance.makeCostReceipt();
		TableData = new Object[1][3];
		TableData[0][0] = prvo.in;
		TableData[0][1] = prvo.out;
		TableData[0][2] = prvo.profit;
	}

	private void iniOperate() {
		// TODO Auto-generated method stub
		
		
		date1 = new DateChooser(this, 210, 88);
		date2 = new DateChooser(this, 210, 110);
		
		
		String[] nameIn = new String[] { "收款日期", "收款金额", "收款人", "快递条形码号" };
		String[] nameOut = new String[] { "付款日期 ", "付款金额", "付款人", "付款账号", "条目", "备注" };
		String[][] dataIn = new String[][] { { "", "", "", "" } };
		String[][] dataOut = new String[][] { { "", "", "", "", "", "" } };

		ImageIcon confirmIcon = new ImageIcon("graphic/account/button/confirm_button.jpg");
		inTable = new JTable(dataIn, nameIn);
		inScroll = new JScrollPane(inTable);
		inScroll.setBounds(144, 137, 320, 144);
		add(inScroll);
		outTable = new JTable(dataOut, nameOut);
		outScroll = new JScrollPane(outTable);
		outScroll.setBounds(144, 294, 320, 144);
		add(outScroll);

		confirm = new JButton(confirmIcon);
		confirm.setBounds(392, 104, 72, 24);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				BussinessVO bvo = finance.checkBusinessCircumstance(date1.getTime(), date2.getTime());

				ArrayList<PaymentSheetVO> in = (ArrayList<PaymentSheetVO>) bvo.in;
				ArrayList<OutVO> out = (ArrayList<OutVO>) bvo.out;

				
				if (bvo != null) {
					warning.setVisible(false);
					// remove(inScroll);
					// remove(outScroll);

					int sizeIn = in.size();
					int sizeOut = out.size();
					String[][] dataIn = new String[sizeIn][4];
					for (int i = 0; i < sizeIn; i++) {
						dataIn[i][0] = in.get(i).getPaymentInformation().getTime();
						dataIn[i][1] = Double.toString(in.get(i).getPaymentInformation().getAmount());
						dataIn[i][0] = in.get(i).getPaymentInformation().getInDeliverId();
						dataIn[i][0] = in.get(i).getBarIds();
					}
					inTable = new JTable(dataIn, nameIn);
					String[][] dataOut = new String[sizeOut][6];
					for (int i = 0; i < sizeOut; i++) {
						dataOut[i][0] = out.get(i).getOutInformation().getDate();
						dataOut[i][1] = Double.toString(out.get(i).getOutInformation().getNum());
						dataOut[i][2] = out.get(i).getOutInformation().getPerson();
						dataOut[i][3] = out.get(i).getOutInformation().getAccount();
						dataOut[i][4] = out.get(i).getOutInformation().getReason();
						dataOut[i][5] = out.get(i).getOutInformation().getComments();

					}
					outTable = new JTable(dataOut, nameOut);

					inScroll = new JScrollPane(inTable);
					outScroll = new JScrollPane(outTable);
					repaint();
				} else {
					warning.setText("日期选择有误，请重新选择");
					warning.setVisible(true);
				}
			}
		});
		add(confirm);
	}

	private void iniAccount() {
		// TODO Auto-generated method stub
		ArrayList<AccountVO> accountlist = account.observeList();
//		TableData = new Object[accountlist.size()][2];
//		for (int i = 0; i < accountlist.size(); i++) {
//			TableData[i][0] = accountlist.get(i).getName();
//			TableData[i][1] = accountlist.get(i).getBalance();
//		}
		String[] title = { "账号", "余额" };

	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background19.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
