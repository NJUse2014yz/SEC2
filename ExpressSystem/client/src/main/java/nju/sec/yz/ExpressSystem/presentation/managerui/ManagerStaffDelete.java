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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.StaffVO;

public class ManagerStaffDelete extends JPanel {
	private StaffBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private JTextField searchnum;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();


	private newJLabel warning = new newJLabel();

	private JButton search;
	private newJBut back;
	private newJBut confirm;

	public ManagerStaffDelete(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
		iniManagerStaffDelete();
	}

	private void iniManagerStaffDelete() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("人员编号");
		name.add("姓名");
		name.add("职务");
		name.add("所属机构");
		name.add("登录帐号");
		
		table=new newTable(data,name,this,false);
		table.setBounds(140,94, 320, 180);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setTableSelect();
		table.join();
		
		changeData(manager.observeStaff());
		System.out.println(manager.observeStaff().size());
	    
	    search=new JButton();
	    search.setBorderPainted(false);
	    search.setBackground(new Color(0,0,255));  
	    search.setOpaque(false); //设置背景透明
	    search.setBounds(436,62,21,21);
	    add(search);
	    
//	    ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new newJBut("返回原列表");
		back.setMargin(new java.awt.Insets(0,0,0,0));;
		back.setBounds(271, 286, 100, 24);
		add(back);
	    
//	    ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new newJBut("删除");
		confirm.setBounds(385, 286, 72, 24);
		add(confirm);
	    
	    
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if (searchnum.getText().equals("")) {
					warning.setText("信息未填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				}else{
					StaffVO staff=manager.observeStaff(searchnum.getText());
					ArrayList<StaffVO> sl=new ArrayList<StaffVO>();
					changeData(sl);
				}
		}
	});
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changeData(manager.observeStaff());
				repaint();
			}
		});	
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int[] deletelines = table.getSelectedRows();
				for (int i = 0; i < deletelines.length; i++) {
					manager.deleteStaff(((String) table.getValueAt(deletelines[i], 4,false)));
				}
				changeData(manager.observeStaff());
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
		System.out.println(allstaff.size());
		for(int i=0;i<allstaff.size();i++){
			Vector<String> vector=new Vector<String>();
			vector.add(allstaff.get(i).getId());
			vector.add(allstaff.get(i).getName());
			vector.add(getpower(allstaff.get(i).getPower()));
			vector.add(allstaff.get(i).getAgency());
			vector.add(allstaff.get(i).getLoginId());
			data.add(vector);
		}
		table.resetData();
	}
		
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background03.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
