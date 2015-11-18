package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserController;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DELIVER_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.DeliverSwitchPanelListener;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainSwitchPanelListener;

public class UserUi extends JPanel{
	private UserBlService userBlService;
	
	private static final int B_WIDTH=490;
	private static final int B_HEIGHT=550;
	private static final int searchin_x=220;
	private static final int searchin_y=146;
	private static final int searchin_w=221;
	private static final int search_x=441;
	private static final int search_y=145;
	private static final int search_w=24;
	private static final int username_x=338;
	private static final int username_y=227;
	private static final int username_w=127;
	private static final int password_x=338;
	private static final int password_y=263;
	private static final int password_w=127;
	private static final int login_x=392;
	private static final int login_y=303;
	private static final int login_w=72;
	private static final int warning_x=290;
	private static final int warning_y=344;
	private static final int warning_w=300;
	private static final int height=24;
	
	private ClientControler controler;
	
	private JTextField JTbarId;
	private JTextField JTuserName;
	private JTextField JTpassword;
	private JButton login;
	private JButton search;
	private JLabel warning;
	
	private ImageIcon login_button=new ImageIcon("graphic/main/button/login_button.png");
	private ImageIcon search_button=new ImageIcon("graphic/main/button/search_button.png");
	private Image background = new ImageIcon("graphic/main/background/main_background.png").getImage();
	
	public UserUi(ClientControler controler)
	{
		super();
		this.userBlService=new UserController();
		this.controler=controler;
		this.initMainUi();
	}
	private void initMainUi() {
		this.setLayout(null);
		this.setSize(B_WIDTH,B_HEIGHT);

		this.JTbarId=new JTextField();
		this.JTbarId.setBounds(searchin_x,searchin_y,searchin_w,height);
		this.add(JTbarId);
		
		this.JTuserName=new JTextField();
		this.JTuserName.setBounds(username_x,username_y,username_w,height);
		this.add(JTuserName);
		
		this.JTpassword=new JTextField();
		this.JTpassword.setBounds(password_x,password_y,password_w,height);
		this.add(JTpassword);
		
		this.login=new JButton(login_button);
		this.login.setBounds(login_x,login_y,login_w, height);
		this.login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(userBlService.login(JTuserName.getText(),JTpassword.getText()).getResult()==Result.SUCCESS)//
				{
					char id=JTuserName.getText().charAt(JTuserName.getText().length()-4);
					switch(id)
					{
					case 'A':
						new MainSwitchPanelListener(MAIN_CONTROL.INVENTORY,controler,0);
						break;
					case 'B':
						new MainSwitchPanelListener(MAIN_CONTROL.TRANSITER,controler,0);
						break;					
					case 'C':
						new MainSwitchPanelListener(MAIN_CONTROL.POSITION,controler,0);
						break;
					case 'D':
						new MainSwitchPanelListener(MAIN_CONTROL.DELIVER,controler,0);
						break;
					case 'E':
						new MainSwitchPanelListener(MAIN_CONTROL.ACCOUNTER,controler,0);
						break;
					case 'F':
						new MainSwitchPanelListener(MAIN_CONTROL.ADMINSTRATER,controler,0);
						break;
					case 'S':
						new MainSwitchPanelListener(MAIN_CONTROL.MANAGER,controler,0);
						break;					
					}
				}
				else
				{
					warning=new JLabel();
					warning.setText("用户名或密码错误，请再次检查");
					warning.setBounds(warning_x,warning_y,warning_w,height);
					warning.setFont(new Font("Dialog",1,12));
					warning.setForeground(Color.red);
					add(warning);
					repaint();
				}
			}
		});
		this.add(login);
		
		this.search=new JButton(search_button);
		this.search.setBounds(search_x,search_y,search_w, height);
		this.search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				//TODO 
			}
		});
		this.add(search);
//		this.repaint();
		this.setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, B_WIDTH,B_HEIGHT, null);
	}
}