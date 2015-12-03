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
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class AdminstraterAddUi extends JPanel{
	private AdminstraterButtonComponents bc;
	private ClientControler mainControler;
	private AdminstraterControler controler;
	private UserBlService userBl;
	
	private JTextField id;
	private JTextField password;
	private JTextField name;
	private JComboBox<String> power;
	private JButton confirm;
	private JLabel warning;
	private String[] job={"快递员","营业厅业务员","中转中心业务员","中转中心仓库管理人员","高级财务人员","低级财务人员","总经理","管理员"};
	
	private static final int id_x=216;
	private static final int id_y=74;
	private static final int id_w=181;
	private static final int id_h=20;
	private static final int password_x=187;
	private static final int password_y=101;
	private static final int password_w=132;
	private static final int password_h=20;
	private static final int pow_x=187;
	private static final int pow_y=128;
	private static final int pow_w=132;
	private static final int pow_h=20;
	private static final int name_x=187;
	private static final int name_y=155;
	private static final int name_w=90;
	private static final int name_h=20;
	private static final int confirm_x=379;
	private static final int confirm_y=171;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon backIcon=new ImageIcon("graphic/adminstrater/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/adminstrater/button/confirm_button.jpg");
	
	public AdminstraterAddUi(ClientControler clientControler,AdminstraterButtonComponents bc) {
		super();
		this.bc=bc;
		this.mainControler=clientControler;
		this.controler=mainControler.adminstraterControler;
		userBl=new UserController();
		initAdminstraterUi();
	}
	private void initAdminstraterUi()
	{
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490,550);
		
		id=new JTextField();
		id.setBounds(id_x, id_y, id_w, id_h);
		add(id);
		
		password=new JTextField();
		password.setBounds(password_x, password_y, password_w, password_h);
		add(password);
		
		power=new JComboBox<String>(job);
		power.setBounds(pow_x, pow_y, pow_w, pow_h);
		add(power);
		
		name=new JTextField();
		name.setBounds(name_x, name_y, name_w, name_h);
		add(name);
		
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
				if(id.getText().equals("")||password.getText().equals(""))
				{
					warning.setText("有必填项未填写");
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
					ResultMessage result=userBl.add(uv);
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

		Image img01 = new ImageIcon("graphic/adminstrater/background/add_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
