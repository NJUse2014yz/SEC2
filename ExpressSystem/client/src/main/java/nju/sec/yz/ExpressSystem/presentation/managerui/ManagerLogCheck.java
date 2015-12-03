package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.accountbl.LogController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.LogBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.LogVO;

public class ManagerLogCheck extends JPanel{

	private LogBlService log=new LogController();
	private ClientControler maincontroler;
private  ManagerButtonComponent mbc;

private String[] title={"时间","人员","操作"};
private String[][] TableData;

private DateChooser date;

private JTable table;
private TableModel model;
private JScrollPane jsc;

private JButton back;
	
	public ManagerLogCheck(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();	
		iniManagerLogCheck();
	}
	

	private void iniManagerLogCheck() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		
		
		TableData=new String[][]{{"","",""}};
		model=new DefaultTableModel(TableData,title);
		table=new JTable(model);
		jsc=new JScrollPane(table);
		jsc.setBounds(143,101,313,185);
		add(jsc);
		
//		original();
		
		
		date=new DateChooser(this,259,63);
		
		date.showDate.getDocument().addDocumentListener(new DocumentListener(){
		   
			@Override
			public void insertUpdate(DocumentEvent e) {
				ArrayList<LogVO> loglist=log.getByTime(date.getTime());
				TableData=new String[loglist.size()][3];
				for(int i=0;i<loglist.size();i++){
					TableData[i][0]=loglist.get(i).getTime();
					TableData[i][1]=loglist.get(i).getPerson();
					TableData[i][2]=loglist.get(i).getOperation();
				}
				model=new DefaultTableModel(TableData,title);
				table.setModel(model);
				table.repaint();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		
		
		ImageIcon backIcon =new ImageIcon("graphic/manager/button/allList.png");
		back=new JButton(backIcon);
		back.setBounds(357,300,90,23);
		add(back);
		
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				original();
			}
		});
	}
	private void original() {
		// TODO Auto-generated method stub
		ArrayList<LogVO> allvo=log.getAll();
		TableData=new String[allvo.size()][3];
		for(int i=0;i<allvo.size();i++){
			TableData[i][0]=allvo.get(i).getTime();
			TableData[i][1]=allvo.get(i).getPerson();
			TableData[i][2]=allvo.get(i).getOperation();
		}
		model=new DefaultTableModel(TableData,title);
		table.setModel(model);
		table.repaint();
	}


	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background20.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
