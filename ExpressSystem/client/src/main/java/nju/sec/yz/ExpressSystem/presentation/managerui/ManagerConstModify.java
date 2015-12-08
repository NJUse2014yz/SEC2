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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.ConstBlService;
import nju.sec.yz.ExpressSystem.common.CityInformation;
import nju.sec.yz.ExpressSystem.common.PriceInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.CityVO;
import nju.sec.yz.ExpressSystem.vo.PriceVO;
import nju.sec.yz.ExpressSystem.vo.SalaryVO;

public class ManagerConstModify extends JPanel {
	private ConstBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	private JTable table;
	private JScrollPane jsc;

	private JTextField priceForCar;
	private JTextField priceForTrain;
	private JTextField priceForPlane;
	private JTextField standard;

	private JButton confirm;

	private JLabel warning = new JLabel();

	public ManagerConstModify(ClientControler maincontroler, ManagerButtonComponent mbc) {
		this.maincontroler = maincontroler;
		this.mbc = mbc;
		mbc.changePanel(this);
		mbc.change();
		iniManagerConstModify();
	}

	private void iniManagerConstModify() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		// table;
		ArrayList<CityVO> cities = (ArrayList<CityVO>) manager.observeAllCity();
		 Object[][] TableData=new Object[cities.size()][3];
		 for(int i=0;i<cities.size();i++){
		 CityInformation temp=cities.get(i).getCityInformation();
		 TableData[i][0]=temp.getFromCity();
		 TableData[i][1]=temp.getToCity();
		 TableData[i][2]=temp.getDistance();
		 }
//		Object[][] TableData = null;
		String[] columnTitle = { "所在地", "编号", "名称" };
		TableModel model = new DefaultTableModel(TableData, columnTitle);
		table = new JTable(model);

		jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(133, 76, 320, 184);
		add(jsc);

		// 四个常量描述
		PriceVO pv = manager.observePrize();
		// PriceInformation pinf=pv.getPriceInformation();

		PriceInformation pinf = new PriceInformation();

		priceForPlane = new JTextField();
		priceForPlane.setText(Double.toString(pinf.getPriceForCar()));
		priceForPlane.setBounds(208, 279, 70, 18);
		add(priceForPlane);

		priceForTrain = new JTextField();
		priceForTrain.setText(Double.toString(pinf.getPriceForCar()));
		priceForTrain.setBounds(208, 308, 70, 18);
		add(priceForTrain);

		priceForCar = new JTextField();
		priceForCar.setText(Double.toString(pinf.getPriceForCar()));
		priceForCar.setBounds(208, 339, 70, 18);
		add(priceForCar);

		standard = new JTextField();
		standard.setText(Double.toString(pinf.getPriceForCar()));
		standard.setBounds(265, 367, 70, 18);
		add(standard);

		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/manager/button/modifyconfirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(377, 380, 72, 25);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((priceForCar.getText().equals("")) || (priceForTrain.getText().equals(""))
						|| (priceForPlane.getText().equals("")) || (standard.getText().equals(""))) {
					warning.setText("尚未完成对带*必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					// translate data
					for (int i = 0; i < cities.size(); i++) {
						CityInformation cityImformation = cities.get(i).getCityInformation();
						cityImformation.setFromCity(table.getValueAt(i, 0).toString());
						cityImformation.setToCity(table.getValueAt(i, 1).toString());
						cityImformation.setDistance((Double) table.getValueAt(i, 0));
						CityVO cv = new CityVO(cityImformation);
						ResultMessage tableresult = manager.modifyCity(cv);
						if (tableresult.getResult() == Result.FAIL) {

							warning.setText(tableresult.getMessage());
							warning.setBounds(138, 490, 463 - 138, 30);
							warning.setFont(new Font("Dialog", 1, 15));
							warning.setForeground(Color.red);
							add(warning);
							repaint();
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
		});
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background15.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
