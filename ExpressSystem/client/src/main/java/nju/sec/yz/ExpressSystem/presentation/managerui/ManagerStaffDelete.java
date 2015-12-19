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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerStaffDelete extends JPanel {
	private StaffBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private JTextField searchnum;
	
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> title=new Vector<String>();

	private JLabel warning = new JLabel();

	private JButton search;
	private JButton back;
	private JButton confirm;

	public ManagerStaffDelete(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerStaffDelete();
	}

	private void iniManagerStaffDelete() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		title.add("人员编号");
		title.add("姓名");
		title.add("职务");
		title.add("所属机构");
		
		table=new newTable(data,title,this,false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setTableSelect();
		table.setBounds(137,94,318,181);
		table.join();

		changeData(manager.observeStaff());
		
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
					StaffVO staff=manager.observeStaff(searchnum.getText());
					ArrayList<StaffVO> sl=new ArrayList<StaffVO>();
					sl.add(staff);
					changeData(sl);
					table.resetData();
				}
		
		}

			
	});
	
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeData(manager.observeStaff());
				table.resetData();
			}
		});	
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int[] deletelines = table.getSelectedRows();
				for (int i = 0; i < deletelines.length; i++) {
					manager.deleteStaff((table.getValueAt(deletelines[i], 0,false)));
					changeData(manager.observeStaff());
					table.resetData();
				}
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
	
	
	private void changeData(ArrayList<StaffVO> allstaff){
		data.removeAllElements();
		for(int i=0;i<allstaff.size();i++){
			Vector<String> vector=new Vector<String>();
			vector.add(allstaff.get(i).getId());
			vector.add(allstaff.get(i).getName());
			vector.add(getpower(allstaff.get(i).getPower()));
			vector.add(allstaff.get(i).getAgency());
			data.add(vector);
		}
		
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
