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

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.CarController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
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
	private newTable table;
	private ArrayList<CarVO> cars;
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	
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
		name.add("车辆代号");
		name.add("车牌号");
		name.add("发动机号");
		name.add("底盘号");
		name.add("购买时间");
		name.add("服役时间");
		cars=carBl.getAll();
		changeData(cars);
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
				if(carBl.getSingle(search.getText())!=null){
					ArrayList<CarVO> cl=new ArrayList<CarVO>();
					cl.add(carBl.getSingle(search.getText()));
					changeData(cl);
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
				changeData(cars);
				table.resetData();
			}
		});
		add(back);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_button_x, confirm_button_y, confirm_button_w, confirm_button_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=carBl.del(data.get(table.getSelectedRow()).get(0));
				if(result.getResult()==Result.SUCCESS){
					warning.setText("删除成功");
					cars.remove(table.getSelectedRow());
					data.remove(table.getSelectedRow());
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
	private void changeData(List<CarVO> cl)
	{
		data.removeAllElements();
		int n=cl.size();
		for(int i=0;i<n;i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(cl.get(i).getId());
			vector.add(cl.get(i).getNumber());
			vector.add(cl.get(i).getMechine());
			vector.add(cl.get(i).getDipan());
			vector.add(cl.get(i).getBuytime());
			vector.add(Integer.toString(cl.get(i).getWorktime()));
			data.add(vector);
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/delete_car_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
