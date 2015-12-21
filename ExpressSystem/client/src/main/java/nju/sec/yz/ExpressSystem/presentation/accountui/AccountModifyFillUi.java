package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.accountbl.AccountController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

public class AccountModifyFillUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private AccountBlService accountBl;
	private AccountVO avo;
	
	private newJText name;
	private newJLabel amount;
	private newJBut confirm;
	private newJLabel warning;
	
	private static final int name_x=198;
	private static final int name_y=75;
	private static final int name_w=152;
	private static final int name_h=18;
	private static final int amount_x=198;
	private static final int amount_y=101;
	private static final int amount_w=70;
	private static final int amount_h=20;
	private static final int confirm_x=381;
	private static final int confirm_y=178;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	
	public AccountModifyFillUi(ClientControler mainControler,AccountButtonComponents bc,String name){
		super();
		this.mainControler=mainControler;
		controler=mainControler.accountControler;
		this.bc=bc;
		accountBl=new AccountController();
		avo=accountBl.observeAccount(name);
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		name=new newJText(avo.getName());
		name.setBounds(name_x, name_y, name_w, name_h);
		add(name);
		
		amount=new newJLabel();
		amount.setBounds(amount_x, amount_y, amount_w, amount_h);
		amount.setText(Double.toString(avo.getBalance()));
		add(amount);
		
		warning=new newJLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				avo.setName(name.getText());
				ResultMessage result=accountBl.modifyAccount(avo);
				if(result.getResult()==Result.SUCCESS)
				{
					warning.setText("提交成功");
				}
				else
				{
					warning.setText(result.getMessage());
				}
				warning.setVisible(true);
				repaint();
			}
		});
		add(confirm);
		
		setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/modify_background2.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
