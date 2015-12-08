package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;

public class ManagerConstAdd extends JPanel{
	private ConstBlService manager=new ManagerController();
	private ClientControler maincontroler;
	private  ManagerButtonComponent mbc;
	
	private JTextField fromCity;
	private JTextField fromID;
	private JTextField toCity;
	private JTextField toID;
	private JTextField distance;
	
	private JButton confirm;
	private JLabel warning=new JLabel();
	
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
		fromID=new JTextField();
		fromID.setBounds(388, 93, 78, 18);
		add(fromID);
		
		toCity=new JTextField();
		toCity.setBounds(210, 93, 91, 18);
		add(toCity);
		
		toID=new JTextField();
		toID.setBounds(388, 64, 78, 18);
		add(toID);
		
		fromCity=new JTextField();
		fromCity.setBounds(210, 64, 91, 18);
		add(fromCity);
		
		distance=new JTextField();
		distance.setBounds(190, 125, 66, 18);
		add(distance);
		
		
		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(365, 166, 76, 27);
		add(confirm);
		setVisible(true);
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((fromCity.getText().equals("")) || (fromID.getText().equals(""))
						|| (toCity.getText().equals("")) || (toID.getText().equals(""))
						|| (distance.getText().equals("")) ) {
					warning.setText("尚未完成对带*必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					// translate data
					CityInformation cityInformation=new CityInformation(fromCity.getText(),fromID.getText(),
							toCity.getText(),toID.getText(),
							Double.parseDouble(distance.getText()));
					
					CityVO cv=new CityVO(cityInformation);
					// 判断输入的信息是否正确
					ResultMessage result = manager.addCity(cv);
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
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background14.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
