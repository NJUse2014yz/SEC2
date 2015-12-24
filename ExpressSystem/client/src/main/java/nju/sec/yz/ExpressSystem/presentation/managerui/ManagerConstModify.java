package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;

public class ManagerConstModify extends JPanel {
	private ConstBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();

	private newJText priceForCar;
	private newJText priceForTrain;
	private newJText priceForPlane;
	private newJText standard;

	private newJBut confirm;

	private newJLabel warning = new newJLabel();
	
	private ArrayList<CityVO> cities;

	public ManagerConstModify(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
//		mbc.change();
		iniManagerConstModify();
	}

	private void iniManagerConstModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("出发地");
		name.add("出发地编号");
		name.add("到达地");
		name.add("到达地编号");
		name.add("距离");
		// table;
		cities = (ArrayList<CityVO>) manager.observeAllCity();
	
		table=new newTable(data,name,this,false);
		table.setBounds(133, 76, 320, 184);
		table.join();
		
		changeData(cities);
		
		// 四个常量描述
		PriceVO pv = manager.observePrize();
		PriceInformation pinf=pv.getPriceInformation();

//		PriceInformation pinf = new PriceInformation();

		priceForPlane = new newJText(Double.toString(pinf.getPriceForPlane()));
		priceForPlane.setBounds(208, 279, 70, 18);
		add(priceForPlane);

		priceForTrain = new newJText(Double.toString(pinf.getPriceForTrain()));
		priceForTrain.setBounds(208, 308, 70, 18);
		add(priceForTrain);

		priceForCar = new newJText(Double.toString(pinf.getPriceForCar()));
		priceForCar.setBounds(208, 339, 70, 18);
		add(priceForCar);

		standard = new newJText(Double.toString(pinf.getStandard()));
		standard.setBounds(265, 367, 70, 18);
		add(standard);

		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/modifyconfirm.png");
		confirm = new newJBut("确认修改");
		confirm.setMargin(new java.awt.Insets(0,0,0,0));
		confirm.setBounds(359, 380, 90, 24);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				Boolean hasBlank=false; 
				for(int c=0;c<cities.size();c++){
					for(int k=0;k<4;k++){
						String str=(String) table.getValueAt(c, k,false);
						if(str==null){
							hasBlank=true;
							break;
						}else{
						str=str.trim();
						str.replace("\n", "");
						if(str.equals("")){
							hasBlank=true;
							break;
						}
						}
					}
				}
				if (hasBlank||(priceForCar.getText().equals("")) || (priceForTrain.getText().equals(""))
						|| (priceForPlane.getText().equals("")) || (standard.getText().equals(""))) {
					warning.NotFilled();
					add(warning);
					repaint();
				} else {
					// translate data
					for (int i = 0; i < cities.size(); i++) {
						CityInformation cityImformation = cities.get(i).getCityInformation();
						cityImformation.setFromCity(table.getValueAt(i, 0,false));
						cityImformation.setFromID(table.getValueAt(i, 1,false));
						cityImformation.setToCity(table.getValueAt(i, 2,false));
						cityImformation.setToID(table.getValueAt(i, 3,false));
						cityImformation.setDistance(Double.parseDouble(table.getValueAt(i, 4,false)));
						CityVO cv = new CityVO(cityImformation);
						ResultMessage tableresult = manager.modifyCity(cv);
						if (tableresult.getResult() == Result.FAIL) {

							warning.setText(tableresult.getMessage());
							warning.setBounds(138, 490, 463 - 138, 30);
							warning.setForeground(Color.red);
							break;
						} else {
							PriceInformation pinf = new PriceInformation();
							pinf.setPriceForCar(Double.parseDouble(priceForCar.getText()));
							pinf.setPriceForPlane(Double.parseDouble(priceForPlane.getText()));
							pinf.setPriceForTrain(Double.parseDouble(priceForTrain.getText()));
							pinf.setStandard(Double.parseDouble(standard.getText()));

							PriceVO pvo = new PriceVO();
							pvo.setPriceInformation(pinf);

							ResultMessage priceresult = manager.modifyPrice(pvo);

							if (priceresult.getResult() == Result.FAIL) {

								warning.setText(priceresult.getMessage());
								warning.setBounds(138, 490, 463 - 138, 30);
								warning.setForeground(Color.red);
							} else {

								// 提交成功
								warning.setText("提交成功");
								warning.setBounds(270, 490, 70, 30);
								warning.setForeground(Color.red);
								warning.setVisible(true);
							}

						}
						add(warning);
						repaint();
					}
				}
			}
		});
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

		Image img01 = new ImageIcon("graphic/manager/background/background15.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
