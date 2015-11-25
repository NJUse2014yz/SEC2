package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.CarController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public class PositionCarDeleteUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private CarBlService carBl;
	private ButtonComponents bc;
	private JTextField search;
	private JButton searchButton;
	private JButton back;
	private JButton confirm;
	private JLabel warning;
	private JTable table;
	private JScrollPane scroll;
	private String[][] data={{"a","f","d","e","f","g","h"},{"a","f","d","e","g","h"},{"a","f","d","e","g","h"},{"a","f","d","e","g","h"},{"a","f","d","e","g","h"},{"a","f","d","e","g","h"}};
	private String[] name={"车辆代号","车牌号","发动机号","底盘号","购买时间","服役时间"};
	private ArrayList<CarVO> cars;
	
	private static final int search_x=227;
	private static final int search_y=66;
	private static final int search_w=222;
	private static final int search_h=20;
	private static final int search_button_x=448;
	private static final int search_button_y=65;
	private static final int search_button_w=22;
	private static final int search_button_h=20;
	private static final int back_button_x=295;
	private static final int back_button_y=326;
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
	public PositionCarDeleteUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		carBl=new CarController();
		cars=carBl.getAll();
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		table=new JTable(data, name);
		table.setRowHeight(20);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll=new JScrollPane(table);
		scroll.setBounds(scroll_x,scroll_y,scroll_w,scroll_h);
		add(scroll);
		
		searchButton=new JButton(searchIcon);
		searchButton.setBounds(search_button_x,search_button_y,search_button_w,search_button_h);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				if(carBl.getSingle(search.getText())!=null){
					CarVO carvo=carBl.getSingle(search.getText());
					data=new String[][]{{carvo.getId(),carvo.getNumber(),carvo.getMechine(),carvo.getBuytime(),Integer.toString(carvo.getWorktime())}};
					repaint();
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
				data=new String[cars.size()][6];
				for(int i=0;i<cars.size();i++)
				{
					data[i][0]=cars.get(i).getId();
					data[i][1]=cars.get(i).getNumber();
					data[i][2]=cars.get(i).getMechine();
					data[i][3]=cars.get(i).getDipan();
					data[i][4]=cars.get(i).getBuytime();
					data[i][5]=Integer.toString(cars.get(i).getWorktime());
				}
//				table=new JTable(data,name);
				search.setText("");
				warning.setVisible(false);
				repaint();
			}
		});
		add(back);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_button_x, confirm_button_y, confirm_button_w, confirm_button_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=carBl.del(data[table.getSelectedRow()][0]);
				if(result.getResult()==Result.SUCCESS){
					warning.setText("删除成功");
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

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/delete_car_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
