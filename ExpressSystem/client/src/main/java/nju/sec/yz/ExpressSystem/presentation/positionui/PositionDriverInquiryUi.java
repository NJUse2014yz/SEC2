package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
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

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.DriverController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class PositionDriverInquiryUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private DriverBlService driverBl;
	private ButtonComponents bc;

	private JTextField search;
	private JButton searchButton;
	private newJLabel warning;
	private newJBut back;
	private newTable table;
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private ArrayList<DriverVO> drivers;
	
	private static final int search_x=226;
	private static final int search_y=66;
	private static final int search_w=222;
	private static final int search_h=20;
	private static final int search_button_x=448;
	private static final int search_button_y=65;
	private static final int search_button_w=22;
	private static final int search_button_h=20;
	private static final int confirm_button_h=24;
	private static final int back_button_x=372;
	private static final int back_button_y=324;
	private static final int back_button_w=100;
	private static final int back_button_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private static final int scroll_x=148;
	private static final int scroll_y=100;
	private static final int scroll_w=319;
	private static final int scroll_h=208;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/position/button/search_button.png");
//	private ImageIcon backIcon=new ImageIcon("graphic/position/button/button_back.png");

	public PositionDriverInquiryUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		driverBl=new DriverController();
		drivers=driverBl.getAll();
		
		name.add("司机编号");
		name.add("姓名");
		name.add("性别");
		name.add("出生日期");
		name.add("身份证号");
		name.add("手机");
		name.add("车辆单位");
		name.add("行驶证期限");
		
		table=new newTable(data,name,this,false);
		table.stopAutoRewidth();
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		
		changeData(drivers);
		
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
//		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		searchButton=new JButton(searchIcon);
		searchButton.setBorderPainted(false);
		searchButton.setBounds(search_button_x,search_button_y,search_button_w,search_button_h);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(driverBl.getSingle(search.getText())!=null){
					ArrayList<DriverVO> dl=new ArrayList<DriverVO>();
					dl.add(driverBl.getSingle(search.getText()));
					changeData(dl);
				}
				else{
					warning.setText("编号输入有误，请重新输入");
					warning.setVisible(true);
				}
			}
		});
		add(searchButton);
		
		search=new JTextField();
		search.setBorder(BorderFactory.createLineBorder(Color.white, 0));
		search.setBounds(search_x, search_y, search_w, search_h);
		add(search);		
		
		back=new newJBut("返回原列表");
		back.setMargin(new java.awt.Insets(0,0,0,0));
		back.setBounds(back_button_x, back_button_y, back_button_w, back_button_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				changeData(drivers);
			}
		});
		add(back);
		
		warning=new newJLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);		
		
		
		setVisible(true);

		
	}
	private void changeData(List<DriverVO> dl)
	{
		data.removeAllElements();
		int n=dl.size();
		for(int i=0;i<n;i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(dl.get(i).getId());
			vector.add(dl.get(i).getName());
			vector.add(e2s(dl.get(i).getSex()));
			vector.add(dl.get(i).getBirthDate());
			vector.add(dl.get(i).getPersonID());
			vector.add(dl.get(i).getPhoneNumber());
			vector.add(dl.get(i).getAgency());
			vector.add(dl.get(i).getLicenseDeadLine());
			data.add(vector);
		}
		table.resetData();
	}
	private String e2s(Sex s)
	{
		if(s.equals(Sex.MALE))
			return "男";
		else
			return "女";
	}
	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/inquiry_driver_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
