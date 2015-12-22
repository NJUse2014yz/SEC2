package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

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
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class ManagerSalaryObserve extends JPanel {
	private SalaryBlService manager = new ManagerController();
	private ClientControler maincontroler;

	private ManagerButtonComponent mbc;

	private ArrayList<SalaryVO> powersalary;
	
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	
	public ManagerSalaryObserve(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
		iniManagerSalaryObserve();
	}

	private void iniManagerSalaryObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("职务");
		name.add("薪水");
		
		powersalary=manager.observeSalary();
		changeData(powersalary);
		table=new newTable(data,name,this,false);
		table.setBounds(138,64,318,181);
		table.join();
		
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
	private void changeData(ArrayList<SalaryVO> powersalary)
	{
		for(int i=0;i<powersalary.size();i++){
			Vector<String> vector=new Vector<String>();
			SalaryImformation temp=powersalary.get(i).getSalaryImformation();
			vector.add(getpower(temp.getPower()));
			vector.add(Integer.toString(temp.getSalary()));
			data.add(vector);
		}
		table.resetData();
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background13.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
