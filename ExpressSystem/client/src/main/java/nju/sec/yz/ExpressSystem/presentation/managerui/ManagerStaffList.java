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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.StaffVO;

public class ManagerStaffList extends JPanel {
	private StaffBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private JTextField searchnum;
	private JTable table;
	private TableModel model;
	private JScrollPane jsc;
	
	private JLabel warning=new JLabel();

	private JButton search;
	private JButton back;

	public ManagerStaffList(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerStaffList();
	}

	private void iniManagerStaffList() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		
		table=new JTable(null);
		
		
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		jsc=new JScrollPane(table);
		jsc.setVisible(true);
	    jsc.setBounds(137,94,318,181);
	    add(jsc);

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
		back.setBounds(370, 286, 81, 20);
		add(back);
	    
	   
	    
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
					StaffVO staff=manager.observeStaff(searchnum.getText());
					
					
					
					String[] columnTitle={"人员编号","姓名","职务","所属机构"};
					String[][] TableData ={{staff.getId(),staff.getName(),getpower(staff.getPower()),staff.getAgency()}};
					model=new DefaultTableModel(TableData,columnTitle);
					table.setModel(model);
					table.repaint();
				}
		
		}

			
	});
		
		table.addMouseListener(new MouseAdapter() {
//			this.setRowSelectionAllowed(true);

			 @Override
			 public void mouseClicked(MouseEvent e) {
//				 if(e.getClickCount()==2){//鼠标双击
					 int num=table.getSelectedRow();
						 maincontroler.mainFrame.nextPanel(new ManagerStaffModify(maincontroler,mbc,table.getValueAt(num, 0).toString()));
					 
					 }
//				 }
			 
		});
	
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				original();
				repaint();
			}
		});	
		
		
	}
	
	private String getpower(Status power) {
		switch(power){
		case DELIVER:
			return "快递员";
		case POSITION:
			return "营业厅业务员";
		case TRANSIT:
			return "中转中心工作人员";
		case JUNIOR_ACCOUNTANCY:
			return "初级财务人员";
		case SENIOR_ACCOUNTANCY:
			return "高级财务人员";
		case MANAGER:
			return "总经理";
		case INVENTORY:
			return "仓库管理人员";
		case ADMINISTRATOR:
			return "管理员";
		default:
			return null;
		}
	}
	
	
	private void original(){
		ArrayList<StaffVO> allstaff=manager.observeStaff();
		
		String[][] TableData = new String[allstaff.size()][4];
		String[] columnTitle={"人员编号","姓名","职务","所属机构"};
		for(int i=0;i<allstaff.size();i++){
			TableData[i][0]=allstaff.get(i).getId();
			TableData[i][1]=allstaff.get(i).getName();
			TableData[i][2]=getpower(allstaff.get(i).getPower());
			TableData[i][3]=allstaff.get(i).getAgency();
			}
		
		model=new DefaultTableModel(TableData,columnTitle);
		table.setModel(model);
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
