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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerStaffModify extends JPanel {
	private StaffBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private String staffId;

	private JButton confirm;
	private JButton back;

	private JLabel warning = new JLabel();

	// 姓名
	private JTextField name;

	// 人员编号
	private JTextField id;

	// 职位，权限
	private JComboBox power;

	// 所属机构
	private JComboBox agency;

	public ManagerStaffModify(ClientControler maincontroler, ManagerButtonComponent mbc, String staffId) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		this.staffId = staffId;
		mbc.changePanel(this);
		mbc.change();
		iniManagerStaffModify();
	}

	private void iniManagerStaffModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		StaffVO vo = manager.observeStaff(staffId);
		
		name=new JTextField(vo.getName());
		name.setBounds(193, 72, 74, 18);
		add(name);
		
		id=new JTextField(vo.getId());
		id.setBounds(218, 102, 83, 18);
		add(id);
		
		String[] status={
				"快递员",
				"营业厅业务员",
				"中转中心工作人员",
				"仓库管理人员",
				"初级财务人员",
				"高级财务人员",
				"总经理",
				"管理员"
		};
		
		power=new JComboBox(status);
		power.setSelectedIndex(getIndexPower(vo.getPower()));
		power.setBounds(187,131,116,20);
		add(power);
		
		ArrayList<String> agencyId=new ArrayList<String>();
		ArrayList<TransitVO> trans=((ManagerController) manager).observeAllTransit();
		int count=0;
		for(int i=0;i<trans.size();i++){
			TransitVO temptra=trans.get(i);	
			ArrayList<PositionVO> pots=(ArrayList<PositionVO>) temptra.getPositions();
			agencyId.add(temptra.getName());
			count++;
			for(int t=0;t<pots.size();t++){
				agencyId.add(pots.get(t).getName());
				count++;
			}
		}
		
		String[] s=new String[count];
		agency=new JComboBox(agencyId.toArray(s));
		agency.setSelectedItem(vo.getAgency());
		agency.setBounds(218,160,110,20);
		add(agency);
		
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(378, 256, 76, 20);
		add(confirm);
		setVisible(true);
		
		ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new JButton(backIcon);
		back.setBounds(290, 256, 81, 20);
		add(back);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((name.getText().equals("")) || (id.getText().equals(""))) {
					warning.setText("尚未完成对带*必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					// translate data
					StaffVO vo=new StaffVO(name.getText(),id.getText(),getstatus(power),agency.getSelectedItem().toString());
					// 判断输入的信息是否正确
					ResultMessage result = manager.addStaff(vo);
					// 失败
					if (result.getResult() == Result.FAIL) {

						warning.setText(result.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					} else {
						// 提交成功
						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);

						repaint();
					}
				}
			}
		});
			
	}
	public static Status getstatus(JComboBox powerType) {
		switch (powerType.getSelectedItem().toString()) {
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

	private int getIndexPower(Status status){
		switch(status){
		case DELIVER:
			return 0;
		case POSITION:
			return 1;
		case TRANSIT:
			return 2;
		case INVENTORY:
			return 3;
		case JUNIOR_ACCOUNTANCY:
			return 4;
		case SENIOR_ACCOUNTANCY:
			return 5;
		case MANAGER:
			return 6;
		case ADMINISTRATOR:
			return 7;
		default:
				return 0;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background07.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}