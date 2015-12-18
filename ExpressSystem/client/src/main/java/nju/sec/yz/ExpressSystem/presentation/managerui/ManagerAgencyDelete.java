package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AgencyListVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyDelete extends JPanel{
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	
	private JTextField searchnum;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	private JLabel warning=new JLabel();
	
	private JButton search;
	private JButton back;
	private JButton confirm;
	
	private ArrayList<TransitVO> transits;
	private ArrayList<PositionVO> positions;
	
	public ManagerAgencyDelete(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerAgencyDelete();
	}

	private void iniManagerAgencyDelete() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("所在地");
		name.add("编号");
		name.add("名称");
		
		table=new newTable(data,name,this,false);
		table.setSelectionMode();
		table.setTableSelect();
		table.setBounds(137,94,318,181);
		table.join();

		original();
	    
	    searchnum=new JTextField();
	    searchnum.setBounds(216, 62, 220, 21);
	    add(searchnum);
	    
	    search=new JButton();
	    search.setBackground(new Color(0,0,255));  
	    search.setOpaque(false); //设置背景透明
	    search.setBounds(436,62,21,21);
	    add(search);
	    
	    ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new JButton(backIcon);
		back.setBounds(290, 286, 81, 20);
		add(back);
	    
	    ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(385, 286, 72, 20);
		add(confirm);
	    
		
		warning.setBounds(198, 490, 463 - 198, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
	    
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if (searchnum.getText().equals("")) {
					warning.setText("信息未填写");
					warning.setVisible(true);
					repaint();
				} else {
					AgencyListVO agency = manager
							.observeTransitByName(searchnum.getText());
					changeData(agency);
					table.resetData();
				}
			}
		});
		
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				original();
				repaint();
			}
		});	
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int[] deletelines=table.getSelectedRows();
				for(int i=0;i<deletelines.length;i++){
					if(deletelines[i]<transits.size()){
						manager.deleteTransit(table.getValueAt(deletelines[i], 1,false));
					}else{
						String transitBelong=positions.get(deletelines[i]-transits.size()).getTransitId();
						manager.deletePosition(transitBelong,table.getValueAt(deletelines[i], 1,false));
					}
					original();
				}
			}
		});	
}
	private	void changeData(AgencyListVO agency)
	{
		transits=(ArrayList<TransitVO>) agency.transits;
		positions=(ArrayList<PositionVO>) agency.positions;
		int total=0;
		total+=transits.size();
		for(int i=0;i<transits.size();i++){
			total+=transits.get(i).getPositions().size();
		}
		for(int i=0;i<transits.size();i++){
			Vector<String> vector=new Vector<String>();
			TransitVO temp=transits.get(i);
			vector.add(temp.getLocation());
			vector.add(temp.getId());
			vector.add(temp.getName());
			for(int j=0;j<positions.size();j++){
				PositionVO temp2=positions.get(j);
				vector.add(temp2.getLocation());
				vector.add(temp2.getId());
				vector.add(temp2.getName());
			}
			data.add(vector);
		}
	}
	
	private void original(){
		changeData(new AgencyListVO(manager.observeAllTransit(),new ArrayList<PositionVO>()));
		table.resetData();
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
