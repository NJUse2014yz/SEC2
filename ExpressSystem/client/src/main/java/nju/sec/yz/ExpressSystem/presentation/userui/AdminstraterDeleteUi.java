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
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AdminstraterControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class AdminstraterDeleteUi extends JPanel{
	private AdminstraterButtonComponents bc;
	private ClientControler mainControler;
	private AdminstraterControler controler;
	private UserBlService userBl;
	
	private JTextField input;
	private JButton search;
	private newTable table;
	private newJBut back;
	private newJBut confirm;
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
	private static final int back_x=261;
	private static final int back_y=300;
	private static final int back_w=110;
	private static final int back_h=25;
	private static final int confirm_x=385;
	private static final int confirm_y=300;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/adminstrater/button/search_button.jpg");
	private ImageIcon backIcon=new ImageIcon("graphic/adminstrater/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/adminstrater/button/confirm_button.jpg");
	
	public AdminstraterDeleteUi(ClientControler clientControler,AdminstraterButtonComponents bc) {
		super();
		this.bc=bc;
		this.mainControler=clientControler;
		this.controler=mainControler.adminstraterControler;
		userBl=new UserController();
		uvl=userBl.getAll();
		name.add("编号");
		name.add("密码");
		name.add("权限");
		name.add("姓名");
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
		input.setBorder(BorderFactory.createLineBorder(Color.white,0));
		input.setBounds(input_x, input_y, input_w, input_h);
		add(input);
		
		add(warning);
		warning.setVisible(false);
		
		search=new JButton(searchIcon);
		search.setBounds(search_x, search_y, search_w, search_h);
		search.setBorderPainted(false);
		search.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				
				UserVO uv=userBl.getSingle(input.getText());
				if(uv!=null)
				{
					ArrayList<UserVO> ul=new ArrayList<UserVO>();
					ul.add(uv);
					if(uv!=null)
					{
						changeData(ul);
						table.resetData();
					}
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
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				uvl=userBl.getAll();
				changeData(uvl);
				table.resetData();
			}
		});
		add(back);
		
		confirm=new newJBut("删除");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=userBl.del(data.get(table.getSelectedRow()).get(0));
				warning.Reply(result);
				if(result.getResult()==Result.SUCCESS)
				{
					uvl=userBl.getAll();//用remove更好?
					changeData(uvl);
					table.resetData();
				}
				repaint();
			}
		});
		add(confirm);
		
		
		
		setVisible(true);
	}
	private void changeData(List<UserVO> ul)
	{
		data.removeAllElements();
		for(int i=0;i<ul.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(ul.get(i).getId());
			vector.add(ul.get(i).getPassword());
			vector.add(getStatus(ul.get(i).getPower()));
			vector.add(ul.get(i).getName());
			data.add(vector);
		}
	}
	public String getStatus(Status po){
		
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
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/adminstrater/background/delete_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
