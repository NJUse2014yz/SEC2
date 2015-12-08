package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class ManagerSalaryObserve extends JPanel {
	private SalaryBlService manager = new ManagerController();
	private ClientControler maincontroler;

	private ManagerButtonComponent mbc;

	private ArrayList<SalaryVO> powersalary;
	
	private JTable table;
	private JScrollPane jsc;

	
	private String[] columnTitle={"职务","薪水"};
	private String[][] TableData={};
	
	public ManagerSalaryObserve(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerSalaryObserve();
	}

	private void iniManagerSalaryObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		
		
		
		powersalary=manager.observeSalary();
		if(powersalary.size()!=0){
		TableData=new String[powersalary.size()][2];
		for(int i=0;i<powersalary.size();i++){
			SalaryImformation temp=powersalary.get(i).getSalaryImformation();
			TableData[i][0]=getpower(temp.getPower());
			TableData[i][1]=Integer.toString(temp.getSalary());
		}
		}
		TableModel model=new DefaultTableModel(TableData,columnTitle);
		table=new JTable(model);
		table.setEnabled(false);
		
		jsc=new JScrollPane(table);
		jsc.setVisible(true);
	    jsc.setBounds(138,64,318,181);
	    add(jsc);
		
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
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background13.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
