package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.userbl.UserController;
import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
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
	private newTable table;
	private newJBut back;
	private newJLabel warning=new newJLabel();
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
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
	private static final int back_x=358;
	private static final int back_y=301;
	private static final int back_w=100;
	private static final int back_h=25;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/adminstrater/button/search_button.jpg");
//	private ImageIcon backIcon=new ImageIcon("graphic/adminstrater/button/back_button.jpg");
//	private ImageIcon confirmIcon=new ImageIcon("graphic/adminstrater/button/confirm_button.jpg");
	
	public AdminstraterInquiryUi(ClientControler clientControler,AdminstraterButtonComponents bc) {
		super();
		this.bc=bc;
		this.mainControler=clientControler;
		this.controler=mainControler.adminstraterControler;
		userBl=new UserController();
		name.add("编号");
		name.add("密码");
		name.add("权限");
		name.add("姓名");
		uvl=userBl.getAll();
		changeData(uvl);
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
		input.setBorder(BorderFactory.createLineBorder(Color.white,0));
		add(input);
		
		search=new JButton(searchIcon);
		search.setBounds(search_x, search_y, search_w, search_h);
		search.setBorderPainted(false);
		search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				uvl=new ArrayList<UserVO>();
				uvl.add(userBl.getSingle(input.getText()));
				if(userBl.getSingle(input.getText())!=null)
				{
					changeData(uvl);
					table.resetData();
				}
			else{
				warning.setText("Id不存在");
				warning.setForeground(Color.red);
				warning.setBounds(138, 490, 463 - 138, 30);
				warning.setVisible(true);
			}
			repaint();
			}
		});
		add(search);
		
		table=new newTable(data,name,this,false);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		
		back=new newJBut("返回原列表");
		back.setBounds(back_x, back_y, back_w, back_h);
		back.setMargin(new java.awt.Insets(0,0,0,0)); 
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				uvl=userBl.getAll();
				changeData(uvl);
				table.resetData();
			}
		});
		add(back);
		
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		setVisible(true);
	}
	private void changeData(List<UserVO> uvl)
	{
		data.removeAllElements();
		int n=uvl.size();
		
		for(int i=0;i<n;i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(uvl.get(i).getId());
			vector.add(uvl.get(i).getPassword());
			vector.add(getStatus(uvl.get(i)));
			vector.add(uvl.get(i).getName());
			data.add(vector);
		}
	}
	private String getStatus(UserVO uv){
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

		Image img01 = new ImageIcon("graphic/adminstrater/background/delete_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
