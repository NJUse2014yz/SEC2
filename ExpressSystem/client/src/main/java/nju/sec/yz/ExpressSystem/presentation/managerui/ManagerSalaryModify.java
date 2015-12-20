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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.SalaryBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SalaryImformation;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class ManagerSalaryModify extends JPanel {
	private SalaryBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private ArrayList<SalaryVO> powersalary;

	private JTable table;
	private JScrollPane jsc;

	private JButton confirm;

	private JLabel warning = new JLabel();

	private String[] columnTitle = { "职务", "薪水" };
	private String[][] TableData = {};

	public ManagerSalaryModify(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerSalaryModify();
	}

	private void iniManagerSalaryModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		powersalary = manager.observeSalary();

		if (powersalary.size() != 0) {
			TableData = new String[powersalary.size()][2];

			for (int i = 0; i < powersalary.size(); i++) {
				SalaryImformation temp = powersalary.get(i).getSalaryImformation();
				TableData[i][0] = getpower(temp.getPower());
				TableData[i][1] = Integer.toString(temp.getSalary());
			}
		}
		TableModel model = new DefaultTableModel(TableData, columnTitle);
		table=new JTable(model);
		table.setEnabled(false);

		jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(138, 64, 318, 190);
		add(jsc);

		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(385, 281, 72, 27);
		add(confirm);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
				// translate data
				for (int i = 0; i < powersalary.size(); i++) {
					SalaryImformation salaryImformation = new SalaryImformation(
							getstatus(table.getValueAt(i, 0).toString()),
							Integer.parseInt((String) table.getValueAt(i, 1)));
					SalaryVO sv = new SalaryVO(salaryImformation);
					ResultMessage result = manager.modifySalary(sv);
					if (result.getResult() == Result.FAIL) {

						warning.setText(result.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
						break;
					} else {
						// 提交成功
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);
					}
				}
			}
			}
		});

	}

	private String getpower(Status power) {
		switch (power) {
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

	public static Status getstatus(String powerType) {
		switch (powerType) {
		case "快递员":
			return Status.DELIVER;
		case "营业厅业务员":
			return Status.POSITION;
		case "中转中心工作人员":
			return Status.TRANSIT;
		case "初级财务人员":
			return Status.JUNIOR_ACCOUNTANCY;
		case "高级财务人员":
			return Status.SENIOR_ACCOUNTANCY;
		case "总经理":
			return Status.MANAGER;
		case "仓库管理人员":
			return Status.INVENTORY;
		case "管理员":
			return Status.ADMINISTRATOR;
		default:
			return null;

		}
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background12.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
