package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ManagerConstObserve extends JPanel {
	private ConstBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();

	private newJLabel priceForCar;
	private newJLabel priceForTrain;
	private newJLabel priceForPlane;
	private newJLabel standard;

	public ManagerConstObserve(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
		iniManagerConstObserve();
	}

	private void iniManagerConstObserve() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		name.add("出发地");
		name.add("出发地编号");
		name.add("到达地");
		name.add("到达地编号");
		name.add("距离");
		
		// table;
		ArrayList<CityVO> cities = (ArrayList<CityVO>) manager.observeAllCity();
		
		table=new newTable(data,name,this,false);
		table.setBounds(133, 76, 300, 184);
		table.join();
		
		changeData(cities);
		
		PriceVO pv = manager.observePrize();
		PriceInformation pinf=pv.getPriceInformation();

		priceForPlane = new newJLabel(Double.toString(pinf.getPriceForPlane()));
		priceForPlane.setBounds(216, 259, 70, 27);
		add(priceForPlane);

		priceForTrain = new newJLabel(Double.toString(pinf.getPriceForTrain()));
		priceForTrain.setBounds(216, 289, 70, 27);
		add(priceForTrain);

		priceForCar = new newJLabel(Double.toString(pinf.getPriceForCar()));
		priceForCar.setBounds(216, 319, 70, 27);
		add(priceForCar);

		standard = new newJLabel(Double.toString(pinf.getStandard()));
		standard.setBounds(286, 348, 70, 27);
		add(standard);

	}
	private void changeData(List<CityVO> cities)
	{
		data.removeAllElements();
		for(int i=0;i<cities.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			CityInformation temp=cities.get(i).getCityInformation();
			
			vector.add(temp.getFromCity());
			vector.add(temp.getFromID());
			vector.add(temp.getToCity());
			vector.add(temp.getToID());
			vector.add(new DecimalFormat(".00").format(temp.getDistance()));
			data.add(vector);
//			if(i==(cities.size()-1))
//			{
//				 System.out.println(TableData[i][1]);
//				 System.out.println(TableData[i][3]);
//			}
		}
		table.resetData();
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background16.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
