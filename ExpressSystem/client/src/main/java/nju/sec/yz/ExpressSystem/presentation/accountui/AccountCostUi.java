package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.FinanceController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;

public class AccountCostUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private FinanceBlSevice financeBl;
	
	private JButton confirm;
	private DateChooser date;
	private JTextField amount;
	private JTextField person;
	private JTextField account;
	private JTextField ps;
	private JComboBox detail;
	private JLabel warning;

	private static final int date_x=236;
	private static final int date_y=63;
	private static final int amount_x=236;
	private static final int amount_y=105;
	private static final int amount_w=90;
	private static final int account_x=236;
	private static final int account_y=150;
	private static final int account_w=197;
	private static final int person_x=236;
	private static final int person_y=195;
	private static final int person_w=90;
	private static final int ps_x=236;
	private static final int ps_y=326;
	private static final int ps_w=90;
	private static final int detail_x=236;
	private static final int detail_y=250;
	private static final int detail_w=90;	
	private static final int height=20;
	private static final int confirm_x=396;
	private static final int confirm_y=459;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	public AccountCostUi(ClientControler mainControler, AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		financeBl=new FinanceController();
		initAccountUi();
		
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		date=new DateChooser(this,date_x,date_y);
		
		amount=new JTextField();
		amount.setBounds(amount_x, amount_y, amount_w, height);
		add(amount);
		account=new JTextField();
		account.setBounds(account_x, account_y, account_w, height);
		add(account);
		person=new JTextField();
		person.setBounds(person_x, person_y, person_w, height);
		add(person);
		detail=new JComboBox(new String[]{"租金","运费","人员工资","奖励"});
		detail.setBounds(detail_x, detail_y, detail_w, height);
		add(detail);
		ps=new JTextField();
		ps.setBounds(ps_x, ps_y, ps_w, height);
		add(ps);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(amount.getText().equals("")||account.getText().equals("")||person.getText().equals("")||ps.getText().equals(""))
				{
					warning.setText("有必填项未填写");
					warning.setVisible(true);
				}
				else
				{
					OutVO outvo=new OutVO(date.getTime(),Integer.parseInt(amount.getText()),person.getText(),account.getText(),detail.getSelectedItem().toString(),ps.getText());
					ResultMessage result=financeBl.makePayment(outvo);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
					}
					else
					{
						warning.setText(result.getMessage());
					}
					warning.setVisible(true);
				}
			}
		});
		add(confirm);
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/cost_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
