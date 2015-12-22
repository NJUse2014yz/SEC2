package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
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
	
	private newJLabel warning=new newJLabel();
	
	private JButton search;
	private newJBut back;
	private newJBut confirm;
	
	private ArrayList<TransitVO> transits;
	private ArrayList<PositionVO> positions;
	
	public ManagerAgencyDelete(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
//		mbc.change();
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
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setTableSelect();
		table.setBounds(137,94,318,181);
		table.join();

		original();
	    
	    searchnum=new JTextField();
	    searchnum.setBorder(BorderFactory.createLineBorder(Color.white,0));
	    searchnum.setBounds(216, 62, 220, 20);
	    add(searchnum);
	    
	    search=new JButton();
	    search.setBorderPainted(false);
	    search.setBackground(new Color(0,0,255));  
	    search.setOpaque(false); //设置背景透明
	    search.setBounds(436,62,21,20);
	    add(search);
	    
	    ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new newJBut("显示全部");
		back.setMargin(new java.awt.Insets(0,0,0,0));
		back.setBounds(281, 286, 90, 24);
		add(back);
	    
//	    ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new newJBut("删除");
		confirm.setBounds(385, 286, 72, 24);
		add(confirm);
	    
		
		warning.setBounds(198, 490, 463 - 198, 30);
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
					if(agency==null)
					{
						warning.setText("不存在该机构");
						warning.setVisible(true);
						repaint();
					}
					else
					{
						warning.setVisible(false);
						changeData(agency);
					}
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
					table.resetData();
				}
			}
		});	
	}
	private	void changeData(AgencyListVO agency)
	{
		data.removeAllElements();
		transits=(ArrayList<TransitVO>) agency.transits;
		positions=(ArrayList<PositionVO>) agency.positions;
		for(int i=0;i<transits.size();i++){
			Vector<String> vector=new Vector<String>();
			TransitVO temp=transits.get(i);
			vector.add(temp.getLocation());
			vector.add(temp.getId());
			vector.add(temp.getName());
			data.add(vector);
		}
		for(int j=0;j<positions.size();j++){
			Vector<String> vector=new Vector<String>();
			PositionVO temp2=positions.get(j);
			vector.add(temp2.getLocation());
			vector.add(temp2.getId());
			vector.add(temp2.getName());
			data.add(vector);
		}
		table.resetData();
	}
	
	private void original(){
		ArrayList<TransitVO> t=manager.observeAllTransit();
		ArrayList<PositionVO> p=new ArrayList<PositionVO>();
		for(int i=0;i<t.size();i++)
		{
			ArrayList<PositionVO> remp=(ArrayList<PositionVO>) t.get(i).positions;
			for(int j=0;j<remp.size();j++)
			{
				p.add(remp.get(j));
			}
		}
		changeData(new AgencyListVO(t,p));
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
