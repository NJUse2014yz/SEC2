package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.userbl.UserController;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ADMINSTRATER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class AdminstraterModifyFillUi extends JPanel{
	private AdminstraterButtonComponents bc;
	private ClientControler mainControler;
	private AdminstraterControler controler;
	private UserBlService userBl;
	
	private UserVO uv;
	private newJText id;
	private newJText password;
	private newJText name;
	private newJCombo power;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	private String[] job={"快递员","营业厅业务员","中转中心业务员","中转中心仓库管理人员","高级财务人员","低级财务人员","总经理","管理员"};
	private newJBut back;

	private static final int back_x=257;
	private static final int back_y=183;
	private static final int back_w=110;
	private static final int back_h=25;
	private static final int id_x=216;
	private static final int id_y=73;
	private static final int id_w=181;
	private static final int id_h=19;
	private static final int password_x=187;
	private static final int password_y=102;
	private static final int password_w=132;
	private static final int password_h=17;
	private static final int pow_x=187;
	private static final int pow_y=127;
	private static final int pow_w=145;
	private static final int pow_h=20;
	private static final int name_x=187;
	private static final int name_y=154;
	private static final int name_w=90;
	private static final int name_h=17;
	private static final int confirm_x=381;
	private static final int confirm_y=183;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
//	private ImageIcon backIcon=new ImageIcon("graphic/adminstrater/button/back_button.jpg");
//	private ImageIcon confirmIcon=new ImageIcon("graphic/adminstrater/button/confirm_button.jpg");
	
	public AdminstraterModifyFillUi(ClientControler clientControler,AdminstraterButtonComponents bc,String id) {
		super();
		this.bc=bc;
		this.mainControler=clientControler;
		this.controler=mainControler.adminstraterControler;
		userBl=new UserController();
		uv=userBl.getSingle(id);
		initAdminstraterUi();
	}
	private void initAdminstraterUi()
	{
		bc.changePanel(this);
//		bc.change();
		setLayout(null);
		setSize(490,550);
		
		id=new newJText(uv.getId());
		id.setBounds(id_x, id_y, id_w, id_h);
		add(id);
		
		password=new newJText(uv.getPassword());
		password.setBounds(password_x, password_y, password_w, password_h);
		add(password);
		
		power=new newJCombo(job);
		Status po=uv.getPower();
		if(po==Status.DELIVER)
		{
			power.setSelectedIndex(0);
		}
		else if(po==Status.POSITION)
		{
			power.setSelectedIndex(1);
		}
		else if(po==Status.TRANSIT)
		{
			power.setSelectedIndex(2);
		}
		else if(po==Status.INVENTORY)
		{
			power.setSelectedIndex(3);
		}
		else if(po==Status.SENIOR_ACCOUNTANCY)
		{
			power.setSelectedIndex(4);
		}
		else if(po==Status.JUNIOR_ACCOUNTANCY)
		{
			power.setSelectedIndex(5);
		}
		else if(po==Status.MANAGER)
		{
			power.setSelectedIndex(6);
		}
		else if(po==Status.ADMINISTRATOR)
		{
			power.setSelectedIndex(7);
		}
		power.setBounds(pow_x, pow_y, pow_w, pow_h);
		add(power);
		
		name=new newJText(uv.getName());
		name.setBounds(name_x, name_y, name_w, name_h);
		add(name);
		
		add(warning);
		warning.setVisible(false);
		
		back=new newJBut("返回原列表");
		back.setMargin(new java.awt.Insets(0,0,0,0)); 
		back.setBounds(back_x, back_y, back_w, back_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				controler.adminstraterChangePanel(ADMINSTRATER_CONTROL.USER_LIST);
			}
		});
		add(back);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(id.getText().equals("")||password.getText().equals(""))
				{
					warning.NotFilled();
				}
				else{
					Status st=Status.ADMINISTRATOR;
					if(power.getSelectedIndex()==0)
					{
						st=Status.DELIVER;
					}
					else if(power.getSelectedIndex()==1)
					{
						st=Status.POSITION;
					}
					else if(power.getSelectedIndex()==2)
					{
						st=Status.TRANSIT;
					}
					else if(power.getSelectedIndex()==3)
					{
						st=Status.INVENTORY;
					}
					else if(power.getSelectedIndex()==4)
					{
						st=Status.SENIOR_ACCOUNTANCY;
					}
					else if(power.getSelectedIndex()==5)
					{
						st=Status.JUNIOR_ACCOUNTANCY;
					}
					else if(power.getSelectedIndex()==6)
					{
						st=Status.MANAGER;
					}
					else if(power.getSelectedIndex()==7)
					{
						st=Status.ADMINISTRATOR;
					}
					UserVO uv=new UserVO(id.getText(),name.getText(),password.getText(),st);
					ResultMessage result=userBl.modify(uv);
					warning.Reply(result);
					if(result.getResult()==Result.SUCCESS){
						confirm.setEnabled(false);
					}
				}
				
				repaint();
			}
		});
		add(confirm);
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/adminstrater/background/add_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
