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
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyModify extends JPanel {
	private AgencyBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private ArrayList<String> num;

	private JTextField location;
	private JTextField Id;
	private JTextField name;
	private JTextField TransitId;

	private JButton confirm;
	private JButton back;

	private JLabel warning = new JLabel();
	private JLabel transit;
	
	
	//记为positon序号
	private int count = 0;
	
	private TransitVO vo;
	
	private ArrayList<PositionVO> listVO;
	

	public ManagerAgencyModify(ClientControler maincontroler, ManagerButtonComponent mbc, ArrayList<String> num) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		this.num = num;
		mbc.changePanel(this);
		mbc.change();
		iniManagerAgencyModify();
	}

	private void iniManagerAgencyModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		location = new JTextField();
		location.setBounds(204, 89, 78, 18);
		add(location);

		Id = new JTextField();
		Id.setBounds(190, 118, 73, 18);
		add(Id);

		name = new JTextField();
		name.setBounds(190, 147, 140, 18);
		add(name);

		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(380, 240, 72, 20);
		add(confirm);

		ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new JButton(backIcon);
		back.setBounds(290, 240, 81, 20);
		add(back);

		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.mainFrame.nextPanel(new ManagerAgencyList(maincontroler, mbc,"modify"));
			}
		});

		if (num.size() == 1) {
			// 修改transit
			TransitVO vo = manager.observeTransit(num.get(0));
			iniTransit(vo);
		} else {
			// 修改position
			TransitVO vo = manager.observeTransit(num.get(0));
			iniPosition(vo, num.get(1));
		}
	}

	private void iniTransit(TransitVO tvo) {
		this.vo=tvo;
		location.setText(this.vo.getLocation());
		name.setText(this.vo.getName());
		Id.setText(this.vo.getId());

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if ((location.getText().equals("")) || (name.getText().equals("")) || (Id.getText().equals(""))) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					vo.setId(Id.getText());
					vo.setLocation(location.getText());
					vo.setName(name.getText());
					ResultMessage result=manager.modifyTransit(vo);
					
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
					}
				}
			}
		});
	}

	private void iniPosition(TransitVO tvo, String Pid) {

		this.vo=tvo;
		listVO = (ArrayList<PositionVO>) vo.getPositions();
		
		for (count=0; !(listVO.get(count).getId() .equals(Pid)) ; count++) {}
		
		location.setText(listVO.get(count).getLocation());
		name.setText(listVO.get(count).getName());
		Id.setText(Pid);
		
		ImageIcon filltransit = new ImageIcon("graphic/manager/button/transit.png");
		transit = new JLabel(filltransit);
		transit.setBounds(147, 182, 89, 21);
		add(transit);

		String transit = listVO.get(count).getTransitId();
		TransitId = new JTextField();
		TransitId.setText(transit);
		TransitId.setBounds(247, 179, 140, 18);
		add(TransitId);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if ((location.getText().equals("")) || (name.getText().equals("")) || (Id.getText().equals(""))
						|| (TransitId.getText().equals(""))) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					//如果transitId没有改变
					if(TransitId.getText().equals(vo.getId())){
						listVO.get(count).setId(Id.getText());
						listVO.get(count).setLocation(location.getText());
						listVO.get(count).setName(name.getText());
						
						vo.setPositions(listVO);
						ResultMessage result=manager.modifyTransit(vo);
						
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

						}
					}else{
						//判断新写入的Transit是否存在
						//不存在
						String traId=TransitId.getText();
						if(manager.observeTransit(traId)==null){
							warning.setText("中转中心编号写入错误");
							warning.setBounds(198, 490, 463 - 198, 30);
							warning.setFont(new Font("Dialog", 1, 15));
							warning.setForeground(Color.red);
							warning.setVisible(true);
							add(warning);
							repaint();
						}else{
							//存在
						    PositionVO temp=listVO.get(count);
						    //在原有transit中删去
						    listVO.remove(count);
						    vo.setPositions(listVO);
						    ResultMessage result=manager.modifyTransit(vo);
						    
						 // 失败
							if (result.getResult() == Result.FAIL) {

								warning.setText(result.getMessage());
								warning.setBounds(138, 490, 463 - 138, 30);
								warning.setFont(new Font("Dialog", 1, 15));
								warning.setForeground(Color.red);
								add(warning);
								repaint();
							} else {
//								// 提交成功
//								warning.setText("提交成功");
//								warning.setBounds(270, 490, 70, 30);
//								warning.setFont(new Font("Dialog", 1, 15));
//								warning.setForeground(Color.red);
//								warning.setVisible(true);
//								add(warning);

							
						   
						    //在现有transit中新增
							TransitVO traVO=manager.observeTransit(traId);
							temp.setId(Id.getText());
							temp.setLocation(location.getText());
							temp.setName(name.getText());
							temp.setTransitId(TransitId.getText());
							ArrayList<PositionVO> polist=(ArrayList<PositionVO>) traVO.getPositions();
							polist.add(temp);
							traVO.setPositions(polist);
							result=manager.modifyTransit(traVO);
							
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
							}
						}
					}
				}
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background05.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
