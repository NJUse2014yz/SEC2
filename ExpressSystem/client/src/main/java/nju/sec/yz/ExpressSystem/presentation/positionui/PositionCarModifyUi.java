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
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public class PositionCarModifyUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private CarBlService carBl;
	private ButtonComponents bc;
	
	private JTextField search;
	private JButton searchButton;
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
	private static final int confirm_button_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private static final int scroll_x=147;
	private static final int scroll_y=99;
	private static final int scroll_w=319;
	private static final int scroll_h=208;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/position/button/search_button.png");

	public PositionCarModifyUi(ClientControler mainControler,ButtonComponents bc){
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
		
//		data=new String[][]{};
		table=new JTable(data, name);
		table.setRowHeight(20);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
//				controler.positionChangePanel(POSITION_CONTROL.CAR_MODIFY2);
				mainControler.mainFrame.nextPanel(new PositionCarModifyFillUi(mainControler,bc,data[table.getSelectedRow()][0]));
			}
		});
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
//					table=new JTable(data,name);
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

		Image img01 = new ImageIcon("graphic/position/background/modify_car_background1.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
