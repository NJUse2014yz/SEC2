package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyObserve extends JPanel{
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private  ManagerButtonComponent mbc;
	
	private ArrayList<String> num;

	private JLabel location;
	private JLabel Id;
	private JLabel name;
	private JLabel TransitId;
	private newTable table;
	private JButton back;

	private JLabel warning = new JLabel();
	private JLabel transit;
	
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> title=new Vector<String>();
	//记为positon序号
		private int count = 0;
	
	public ManagerAgencyObserve(ClientControler maincontroler,ManagerButtonComponent mbc,ArrayList<String> num) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		this.num=num;
		mbc.changePanel(this);
		mbc.change();
		iniManagerAgencyObserve();
	}

	private void iniManagerAgencyObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		location = new JLabel();
		location.setBounds(204, 89, 78, 18);
		add(location);
		location.setFont(new Font("Dialog", 1, 15));
		location.setForeground(Color.white);
		
		Id = new JLabel();
		Id.setBounds(190, 118, 73, 18);
		add(Id);
		Id.setFont(new Font("Dialog", 1, 15));
		Id.setForeground(Color.white);

		name = new JLabel();
		name.setBounds(190, 147, 180, 18);
		add(name);
		name.setFont(new Font("Dialog", 1, 15));
		name.setForeground(Color.white);

		title.add("所在地");
		title.add("编号");
		title.add("名称");
		
		table=new newTable(data,title,this,false);
		table.setBounds(137,200,318,181);
		table.join();
		
		ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new JButton(backIcon);
		back.setBounds(370, 360, 81, 20);
		add(back);

		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.mainFrame.nextPanel(new ManagerAgencyList(maincontroler, mbc,"observe"));
			}
		});
		
		if (num.size() == 1) {
			// 修改transit
			TransitVO vo = manager.observeTransit(num.get(0));
			iniTransit(vo);
		} else {
			// 修改position
			TransitVO vo = manager.observeTransit(num.get(0));
			iniPosition(vo, num.get(1));
		}
	}
	private void iniTransit(TransitVO vo) {
		location.setText(vo.getLocation());
		name.setText(vo.getName());
		Id.setText(vo.getId());
		changeData(vo);
	}
	
	private void iniPosition(TransitVO vo, String Pid) {
	
		ArrayList<PositionVO> listVO = (ArrayList<PositionVO>) vo.getPositions();
		
		for (;!( listVO.get(count).getId() .equals(Pid)); count++) {}
		
		location.setText(listVO.get(count).getLocation());
		name.setText(listVO.get(count).getName());
		Id.setText(Pid);
		
		ImageIcon filltransit = new ImageIcon("graphic/manager/button/transit.png");
		transit = new JLabel(filltransit);
		transit.setBounds(147, 179, 89, 21);
		add(transit);
	
		String transit = listVO.get(count).getTransitId();
		TransitId = new JLabel();
		TransitId.setText(transit);
		TransitId.setBounds(247, 179, 140, 18);
		add(TransitId);
		TransitId.setFont(new Font("Dialog", 1, 15));
		TransitId.setForeground(Color.white);
	
		
	}
	private void changeData(TransitVO vo)
	{
		data.removeAllElements();
		for(int i=0;i<vo.getPositions().size();i++){
			Vector<String> vector=new Vector<String>();
			PositionVO temp=vo.getPositions().get(i);
			vector.add(temp.getLocation());
			vector.add(temp.getId());
			vector.add(temp.getName());
			data.add(vector);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
	
		Image img01 = new ImageIcon("graphic/manager/background/background05.png").getImage();
	
		g.drawImage(img01, 0, 0, 490, 550, null);
	
	}
}
