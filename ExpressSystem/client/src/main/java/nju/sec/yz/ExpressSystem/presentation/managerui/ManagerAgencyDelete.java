package nju.sec.yz.ExpressSystem.presentation.managerui;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;

public class ManagerAgencyDelete extends JPanel{
	private AgencyBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	
	private JTextField searchnum;
	private JTable table;
	private TableModel model;
	private JScrollPane jsc;
	
	private JLabel warning=new JLabel();
	
	private JButton search;
	private JButton back;
	private JButton confirm;
	
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
	    
	    
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if (searchnum.getText().equals("")) {
					warning.setText("信息未填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				}else{
					
				}
		
		}
	});
		
		
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				original();
				repaint();
			}
		});	
}
	
	
	private void original(){
//		ArrayList<TransitVO> alltransits=manager.observeAllTransit();
		ArrayList<PositionVO> inf=new ArrayList<PositionVO>();
		String[][] TableData = null;
		String[] columnTitle={"所在地","编号","名称"};
//		for(int i=0;i<alltransits.size();i++){
//			TransitVO temp=alltransits.get(i);
//			TableData[i][0]=temp.getLocation();
//			TableData[i][1]=temp.getId();
//			TableData[i][2]=temp.getName();
//			inf.addAll(temp.getPositions());
//			}
//		for(int i=0;i<inf.size();i++){
//			PositionVO temp=inf.get(i);
//			TableData[i+alltransits.size()][0]=temp.getLocation();
//			TableData[i+alltransits.size()][1]=temp.getId();
//			TableData[i+alltransits.size()][2]=temp.getName();
//		}
		model=new DefaultTableModel(TableData,columnTitle);
		table=new JTable(model);
		jsc=new JScrollPane(table);
		jsc.setVisible(true);
	    jsc.setBounds(137,94,318,181);
	    add(jsc);
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
