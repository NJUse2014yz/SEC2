package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.userbl.UserController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainControl;

public class UserUi extends JPanel{
	private UserBlService userBlService;
	private DeliverBlService deliverBlService;
	
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
	private static final int warning_x=200;
	private static final int warning_y=344;
	private static final int warning_w=300;
	private static final int height=24;
	
	private ClientControler controler;
	
	private JTextField JTbarId;
	private JTextField JTuserName;
	private JPasswordField JTpassword;
	private newJBut login;
	private JButton search;
	private newJLabel warning;
	
//	private ImageIcon login_button=new ImageIcon("graphic/main/button/login_button.png");
	private ImageIcon search_button=new ImageIcon("graphic/main/button/new_search.png");
	private Image background = new ImageIcon("graphic/main/background/main_background.png").getImage();
	// 退出系统
	private JButton exitButton;
	
	ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
	public UserUi(ClientControler controler)
	{
		super();
		this.userBlService=new UserController();
		this.deliverBlService=new DeliverController();
		this.controler=controler;
		this.initMainUi();
	}
	private void initMainUi() {
		this.setLayout(null);
		this.setSize(B_WIDTH,B_HEIGHT);

		this.warning=new newJLabel();
		this.warning.setForeground(Color.red);
		this.warning.setBounds(warning_x,warning_y,warning_w,height);
		this.add(warning);
		this.warning.setVisible(false);
		
		
		
		this.JTbarId=new JTextField();
		JTbarId.setBorder(BorderFactory.createLineBorder(Color.white,0));
		this.JTbarId.setBounds(searchin_x,searchin_y,searchin_w,height);
		this.add(JTbarId);
		
		this.JTuserName=new JTextField();
		JTuserName.setBorder(BorderFactory.createLineBorder(Color.white,0));
		this.JTuserName.setBounds(username_x,username_y,username_w,height);
		this.add(JTuserName);
		
		this.JTpassword=new JPasswordField();
		JTpassword.setBorder(BorderFactory.createLineBorder(Color.white,0));
		this.JTpassword.setBounds(password_x,password_y,password_w,height);
		this.add(JTpassword);
		
		Font font = new Font("Microsoft YaHei",Font.PLAIN,15);
		JTbarId.setFont(font);
		JTuserName.setFont(font);
		JTpassword.setFont(font);
		
		
		
		this.login=new newJBut("登录");
		this.login.setBounds(login_x,login_y,login_w, height);
		this.login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(userBlService.login(JTuserName.getText(),JTpassword.getText()).getResult()==Result.SUCCESS)

				{	
					if(JTpassword.getText().equals("admin"))
					{
						controler.mainChangePanel(MainControl.ADMINSTRATER);
					}
					else
					{
						char id=JTuserName.getText().charAt(JTuserName.getText().length()-4);
						switch(id)
						{
						case 'A':
							controler.mainChangePanel(MainControl.INVENTORY);
							break;
						case 'B':
							controler.mainChangePanel(MainControl.TRANSITER);
							break;					
						case 'C':
							controler.mainChangePanel(MainControl.POSITION);
							break;
						case 'D':
							controler.mainChangePanel(MainControl.DELIVER);
							break;
						case 'E':
							controler.mainChangePanel(MainControl.SENIOR_ACCOUNTANCY);
							break;
						case 'F':
							controler.mainChangePanel(MainControl.ADMINSTRATER);
							break;
						case 'S':
							controler.mainChangePanel(MainControl.MANAGER);
							break;	
						case 'e':
							controler.mainChangePanel(MainControl.JUNIOR_ACCOUNTANCY);
							break;	
						}
					}
				}
				else
				{
					warning.setVisible(true);
					warning.setText(userBlService.login(JTuserName.getText(),JTpassword.getText()).getMessage());
					repaint();
				}
			}
		});
		this.add(login);
		
		this.search=new JButton(search_button);
		search.setBorderPainted(false);
		this.search.setBounds(search_x,search_y,search_w, height);
		this.search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(deliverBlService.checkDeliver(JTbarId.getText())!=null)
				{
					controler.mainFrame.nextPanel(new UserOrderSearchUi(controler,JTbarId.getText()));
				}
				else
				{
					warning.setVisible(true);
					warning.setText("订单条形码号有误，请再次检查");
					repaint();
				}
			}
		});
		this.add(search);
		
		exitButton= new JButton(ExitIcon);
		exitButton.setOpaque(false);
		exitButton.setBorder(null);
		exitButton.setContentAreaFilled(false); 
		exitButton.setBounds(490-19,0,19,19);
		add(exitButton);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
//		this.repaint();
		this.setVisible(true);
	}
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, B_WIDTH,B_HEIGHT, null);
	}
}
