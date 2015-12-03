package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.userbl.UserController;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class AdminstraterInquiryUi extends JPanel{
	private AdminstraterButtonComponents bc;
	private ClientControler mainControler;
	private AdminstraterControler controler;
	private UserBlService userBl;
	
	private JTextField input;
	private JButton search;
	private JTable table;
	private JScrollPane scroll;
	private JButton back;
	private JLabel warning;
	private String[] name={"编号","密码","权限","姓名"};
	private String[][] data={{"","","",""}};
	private List<UserVO> uvl;
	
	private static final int input_x=214;
	private static final int input_y=67;
	private static final int input_w=220;
	private static final int input_h=20;
	private static final int search_x=433;
	private static final int search_y=66;
	private static final int search_w=23;
	private static final int search_h=21;
	private static final int scroll_x=137;
	private static final int scroll_y=98;
	private static final int scroll_w=319;
	private static final int scroll_h=191;
	private static final int back_x=378;
	private static final int back_y=301;
	private static final int back_w=80;
	private static final int back_h=25;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/adminstrater/button/search_button.jpg");
	private ImageIcon backIcon=new ImageIcon("graphic/adminstrater/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/adminstrater/button/confirm_button.jpg");
	
	public AdminstraterInquiryUi(ClientControler clientControler,AdminstraterButtonComponents bc) {
		super();
		this.bc=bc;
		this.mainControler=clientControler;
		this.controler=mainControler.adminstraterControler;
		userBl=new UserController();
		uvl=userBl.getAll();
		int n=uvl.size();
		data=new String[n][4];
		for(int i=0;i<n;i++)
		{
			data[i][0]=uvl.get(i).getId();
			data[i][1]=uvl.get(i).getPassword();
			data[i][2]=getStatus(uvl.get(i));
			data[i][3]=uvl.get(i).getName();
		}
		initAdminstraterUi();
	}
	private void initAdminstraterUi()
	{
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490,550);
		
		input=new JTextField();
		input.setBounds(input_x, input_y, input_w, input_h);
		add(input);
		
		search=new JButton(searchIcon);
		search.setBounds(search_x, search_y, search_w, search_h);
		search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				UserVO uv=userBl.getSingle(input.getText());
				if(getStatus(uv)!=null)
				{
					data=new String[][]{{uv.getId(),uv.getPassword(),getStatus(uv),uv.getName()}};
					remove(scroll);
					table=new JTable(data,name);
					table.setRowHeight(20);
					scroll=new JScrollPane(table);
					scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
					add(scroll);
				}
			}
		});
		add(search);
		
		table=new JTable(data,name);
		table.setRowHeight(20);
		scroll=new JScrollPane(table);
		scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		add(scroll);
		
		back=new JButton(backIcon);
		back.setBounds(back_x, back_y, back_w, back_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				uvl=userBl.getAll();
				int n=uvl.size();
				data=new String[n][4];
				for(int i=0;i<n;i++)
				{
					data[i][0]=uvl.get(i).getId();
					data[i][1]=uvl.get(i).getPassword();
					data[i][2]=getStatus(uvl.get(i));
					data[i][3]=uvl.get(i).getName();
				}
				remove(scroll);
				table=new JTable(data,name);
				table.setRowHeight(20);
				scroll=new JScrollPane(table);
				scroll.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
				add(scroll);
			}
		});
		add(back);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		setVisible(true);
	}
	public String getStatus(UserVO uv){
		if(uv!=null)
		{
			Status po=uv.getPower();
			if(po==Status.DELIVER)
			{
				return "快递员";
			}
			else if(po==Status.POSITION)
			{
				return "营业厅业务员";
			}
			else if(po==Status.TRANSIT)
			{
				return "中转中心业务员";
			}
			else if(po==Status.INVENTORY)
			{
				return "中转中心从仓库管理人员";
			}
			else if(po==Status.SENIOR_ACCOUNTANCY)
			{
				return "高级财务人员";
			}
			else if(po==Status.JUNIOR_ACCOUNTANCY)
			{
				return "低级财务人员";
			}
			else if(po==Status.MANAGER)
			{
				return "总经理";
			}
			else 
			{
				return "管理员";
			}
		}
		else
			return null;
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/adminstrater/background/inquiry_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
