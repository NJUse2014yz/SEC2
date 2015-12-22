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
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerAgencyModify extends JPanel {
	private AgencyBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;
	private ArrayList<String> num;

	private newJText location;
	private newJText Id;
	private newJText name;
	private newJText TransitId;

	private newJBut confirm;
	private newJBut back;

	private newJLabel warning = new newJLabel();
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
//		mbc.change();
		iniManagerAgencyModify();
	}

	private void iniManagerAgencyModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		location = new newJText();
		location.setBounds(204, 89, 78, 18);
		add(location);

		Id = new newJText();
		Id.setBounds(190, 118, 73, 18);
		add(Id);

		name = new newJText();
		name.setBounds(190, 147, 140, 18);
		add(name);

		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(380, 240, 72, 20);
		add(confirm);

//		ImageIcon backIcon = new ImageIcon("graphic/manager/button/back.png");
		back = new newJBut("返回原列表");
		back.setMargin(new java.awt.Insets(0,0,0,0));
		back.setBounds(271, 240, 100, 24);
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
					warning.setForeground(Color.red);
					warning.setVisible(true);
				} else {
					vo.setId(Id.getText());
					vo.setLocation(location.getText());
					vo.setName(name.getText());
					ResultMessage result=manager.modifyTransit(vo);
					
					warning.Reply(result);
				}
				add(warning);
				repaint();
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
		TransitId = new newJText();
		TransitId.setText(transit);
		TransitId.setBounds(247, 179, 140, 18);
		add(TransitId);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if ((location.getText().equals("")) || (name.getText().equals("")) || (Id.getText().equals(""))
						|| (TransitId.getText().equals(""))) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setForeground(Color.red);
					warning.setVisible(true);
				} else {
					//如果transitId没有改变
					if(TransitId.getText().equals(vo.getId())){
						listVO.get(count).setId(Id.getText());
						listVO.get(count).setLocation(location.getText());
						listVO.get(count).setName(name.getText());
						
						vo.setPositions(listVO);
						ResultMessage result=manager.modifyTransit(vo);
						
						warning.Reply(result);
					}else{
						//判断新写入的Transit是否存在
						//不存在
						warning.setForeground(Color.red);
						
						String traId=TransitId.getText();
						if(manager.observeTransit(traId)==null){
							warning.setText("中转中心编号写入错误");
							warning.setBounds(198, 490, 463 - 198, 30);
							warning.setVisible(true);
						}else{
							//存在
						    PositionVO temp=listVO.get(count);
						    //在原有transit中删去
						    listVO.remove(count);
						    vo.setPositions(listVO);
						    ResultMessage result=manager.modifyTransit(vo);
						    warning.setText("");
						 // 失败
							if (result.getResult() == Result.FAIL) {

								warning.setText(result.getMessage());
								warning.setBounds(138, 490, 463 - 138, 30);
							} else {

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
							warning.Reply(result);
							
						}
					}
				}
				}
				add(warning);
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background05.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
