package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;

public class ManagerConstAdd extends JPanel{
	private ConstBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private  ManagerButtonComponent mbc;
	
	private newJText fromCity;
	private newJText fromID;
	private newJText toCity;
	private newJText toID;
	private newJText distance;
	
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	
	public ManagerConstAdd(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerConstAdd();
	}

	private void iniManagerConstAdd() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		/**
		 * @author cong
		 * 交换id和名称坐标
		 */
		toCity=new newJText();
		toCity.setBounds(210, 93, 91, 18);
		add(toCity);
		
		toID=new newJText();
		toID.setBounds(388, 93, 78, 18);
		add(toID);
		
		fromCity=new newJText();
		fromCity.setBounds(210, 64, 91, 18);
		add(fromCity);
		
		fromID=new newJText();
		fromID.setBounds(388, 64, 78, 18);
		add(fromID);
		
		
		
		distance=new newJText();
		distance.setBounds(190, 125, 66, 18);
		add(distance);
		
		
		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new newJBut("确定");
		confirm.setBounds(365, 166, 72, 24);
		add(confirm);
		setVisible(true);
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((fromCity.getText().equals("")) || (fromID.getText().equals(""))
						|| (toCity.getText().equals("")) || (toID.getText().equals(""))
						|| (distance.getText().equals("")) ) {
					warning.NotFilled();
				} else {
					// translate data
					CityInformation cityInformation=new CityInformation(fromCity.getText(),fromID.getText(),
							toCity.getText(),toID.getText(),
							Double.parseDouble(distance.getText()));
					
					CityVO cv=new CityVO(cityInformation);
					// 判断输入的信息是否正确
					ResultMessage result = manager.addCity(cv);
						warning.Reply(result);
				}
				add(warning);
				repaint();
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background14.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
