package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ManagerConstObserve extends JPanel {
	private ConstBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private JTable table;
	private JScrollPane jsc;

	private JLabel priceForCar;
	private JLabel priceForTrain;
	private JLabel priceForPlane;
	private JLabel standard;

	public ManagerConstObserve(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerConstObserve();
	}

	private void iniManagerConstObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		// table;
		ArrayList<CityVO> cities = (ArrayList<CityVO>) manager.observeAllCity();
		Object[][] TableData = new Object[cities.size()][5];
		for (int i = 0; i < cities.size(); i++) {
			CityInformation temp = cities.get(i).getCityInformation();
			TableData[i][0] = temp.getFromCity();
			TableData[i][1] = temp.getFromID();
			TableData[i][2] = temp.getToCity();
			TableData[i][3] = temp.getToID();
			TableData[i][4] = temp.getDistance();
		}
		// Object[][] TableData = null;
		String[] columnTitle = { "出发地", "出发地编号", "到达地", "到达地编号", "距离" };
		TableModel model = new DefaultTableModel(TableData, columnTitle);
		table = new JTable(model);

		jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(143, 63, 320, 184);
		add(jsc);

		PriceVO pv = manager.observePrize();
		 PriceInformation pinf=pv.getPriceInformation();


		priceForPlane = new JLabel();
		priceForPlane.setText(Double.toString(pinf.getPriceForPlane()));
		priceForPlane.setBounds(216, 259, 70, 27);
		priceForPlane.setForeground(Color.GRAY);
		priceForPlane.setFont(new Font("Dialog", 0, 18));
		add(priceForPlane);

		priceForTrain = new JLabel();
		priceForTrain.setText(Double.toString(pinf.getPriceForTrain()));
		priceForTrain.setBounds(216, 289, 70, 27);
		priceForTrain.setForeground(Color.GRAY);
		priceForTrain.setFont(new Font("Dialog", 0, 18));
		add(priceForTrain);

		priceForCar = new JLabel();
		priceForCar.setText(Double.toString(pinf.getPriceForCar()));
		priceForCar.setBounds(216, 319, 70, 27);
		priceForCar.setForeground(Color.GRAY);
		priceForCar.setFont(new Font("Dialog", 0, 18));
		add(priceForCar);

		standard = new JLabel();
		standard.setText(Double.toString(pinf.getStandard()));
		standard.setBounds(286, 348, 70, 27);
		standard.setForeground(Color.GRAY);
		standard.setFont(new Font("Dialog", 0, 18));
		add(standard);

	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background16.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
