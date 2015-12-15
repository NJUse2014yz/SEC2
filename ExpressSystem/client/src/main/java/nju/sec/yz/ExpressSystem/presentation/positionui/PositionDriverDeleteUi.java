package nju.sec.yz.ExpressSystem.presentation.positionui;
/**
 * 可以多条一起删
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.DriverController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class PositionDriverDeleteUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private ButtonComponents bc;
	private DriverBlService driverBl;
	private JTextField search;
	private JButton searchButton;
	private JButton back;
	private JButton confirm;
	private JLabel warning;
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
	private static final int back_button_x=305;
	private static final int back_button_y=325;
	private static final int back_button_w=81;
	private static final int back_button_h=20;
	private static final int confirm_button_x=400;
	private static final int confirm_button_y=324;
	private static final int confirm_button_w=72;
	private static final int confirm_button_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private static final int scroll_x=148;
	private static final int scroll_y=100;
	private static final int scroll_w=319;
	private static final int scroll_h=208;
	
	
	private ImageIcon searchIcon=new ImageIcon("graphic/position/button/search_button.png");
	private ImageIcon backIcon=new ImageIcon("graphic/position/button/button_back.png");
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	

	public PositionDriverDeleteUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		driverBl=new DriverController();
		drivers=driverBl.getAll();
		changeData(drivers);
		name.add("司机编号");
		name.add("姓名");
		name.add("性别");
		name.add("出生日期");
		name.add("身份证号");
		name.add("手机");
		name.add("车辆单位");
		name.add("行驶证期限");
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new newTable(data,name,this,false);
		table.stopAutoRewidth();
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		
		searchButton=new JButton(searchIcon);
		searchButton.setBounds(search_button_x,search_button_y,search_button_w,search_button_h);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(driverBl.getSingle(search.getText())!=null){
					ArrayList<DriverVO> dl=new ArrayList<DriverVO>();
					dl.add(driverBl.getSingle(search.getText()));
					changeData(dl);
					table.resetData();
				}
				else{
					warning.setText("编号输入有误，请重新输入");
					warning.setVisible(true);
				}
			}
		});
		add(searchButton);
		
		search=new JTextField();
		search.setBounds(search_x, search_y, search_w, search_h);
		add(search);
		
		back=new JButton(backIcon);
		back.setBounds(back_button_x, back_button_y, back_button_w, back_button_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				changeData(drivers);
				table.resetData();
			}
		});
		add(back);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_button_x, confirm_button_y, confirm_button_w, confirm_button_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=driverBl.del(data.get(table.getSelectedRow()).get(0));
				if(result.getResult()==Result.SUCCESS){
					warning.setText("删除成功");
					drivers.remove(table.getSelectedRow());
					table.resetData();
				}
				else
				{
					warning.setText(result.getMessage());
				}
			}
		});
		add(confirm);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
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

		Image img01 = new ImageIcon("graphic/position/background/delete_driver_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
