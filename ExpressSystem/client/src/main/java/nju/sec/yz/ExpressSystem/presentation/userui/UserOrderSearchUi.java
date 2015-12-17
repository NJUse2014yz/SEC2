package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import nju.sec.yz.ExpressSystem.bl.deliverbl.Deliver;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

public class UserOrderSearchUi extends JPanel {
	ClientControler mainControler;
	DeliverBlService deliver=new DeliverController();
	// 确定选项
	private JButton confirmButton;
	private JTextField searchnumber;
	//提示信息
	private JLabel warning;
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private JLabel leaveButton;
	
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	private List<String> trails; 
	
	private String id;
	private DeliverVO ordervo;
	
	ImageIcon SearchIcon=new ImageIcon("graphic/adminstrater/button/search_button.jpg");
	ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
	
	public UserOrderSearchUi(ClientControler controler,String id) {
		mainControler=controler;
		this.id=id;
		ordervo=deliver.checkDeliver(id);
		name.add("物流轨迹");
		name.add("时间");
		trails= ordervo.trails;
		changeData(trails);
		initDeliverOrderSearch();
	}
	private void initDeliverOrderSearch() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		confirmButton = new JButton(SearchIcon);
		confirmButton.setBounds(441, 72, 23, 21);
		add(confirmButton);

		warning=new JLabel();
		warning.setBounds(250,490,100,30);
		warning.setFont(new Font("Dialog",1,15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
		
		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ordervo=deliver.checkDeliver(searchnumber.getText());
				if(ordervo==null){
					warning.setText("输入信息错误");
					warning.setVisible(true);
					repaint();
				}else{
					changeData(ordervo.trails);
					table.resetData();
				}
			}
			});
		
		searchnumber = new JTextField();
		searchnumber.setBounds(221, 72, 219, 20);
		add(searchnumber);
		
		//离开当前账户
		leaveButton=new JLabel();
		leaveButton.setBounds(433, 21, 37, 20);
		add(leaveButton);			
		
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
		
		
		leaveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mainControler.mainChangePanel(MAIN_CONTROL.LOGIN);
			}
		});
		
		table = new newTable(data, name, this, false);
		table.setBounds(144, 105, 315, 177);
		table.join();
		
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/adminstrater/background/ordersearch_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

	private void changeData(List<String> trails) {
		if(warning!=null)
		{
			warning.setVisible(false);
		}
		data.removeAllElements();
		for (int i = 0; i < trails.size(); i++) {
			String[] temp;
			temp = trails.get(i).split(" ");
			Vector<String> vector = new Vector<String>();
			vector.add(temp[0]);
			vector.add(temp[1]);
			data.add(vector);
		}
	}
}



