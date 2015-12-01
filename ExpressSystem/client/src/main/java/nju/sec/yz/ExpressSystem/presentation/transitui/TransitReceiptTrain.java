package nju.sec.yz.ExpressSystem.presentation.transitui;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.TransitFlightInformation;
import nju.sec.yz.ExpressSystem.common.TransportType;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class TransitReceiptTrain extends JPanel {
	DeliverBlService deliverblservice = new DeliverController();
	private AgencyBlService manager = new ManagerController();
	
	ClientControler maincontrol;
	TransitButtonComponents tbc;

	private JTextField trainId;
	private JTextField carriageId;
	private JTextField transiterId;

	private JComboBox departure;
	private JComboBox destination;
	private JTable barId;
	private TableModel model;

	private JButton confirm;

	private JLabel trainTransitId;
	private JLabel fare;
	private JLabel warning = new JLabel();

	public TransitReceiptTrain(ClientControler maincontrol, TransitButtonComponents tbc) {
		this.maincontrol = maincontrol;
		this.tbc = tbc;
		tbc.setNextPanel(this);
		tbc.change();
		iniTransReceiptTrain();

	}

	private void iniTransReceiptTrain() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		DateChooser date = new DateChooser(this, 212, 81);

		ArrayList<TransitVO> trans = manager.observeAllTransit();
		String[] transitAgency = new String[trans.size()];
		for (int i = 0; i < trans.size(); i++) {
			transitAgency[i] = trans.get(i).getName();
		}

		departure = new JComboBox(transitAgency);
		departure.setBounds(198, 56, 80, 20);
		add(departure);

		destination = new JComboBox(transitAgency);
		destination.setBounds(346, 56, 80, 20);
		add(destination);

		trainId = new JTextField();
		trainId.setBounds(192, 110, 184, 18);
		add(trainId);

		carriageId = new JTextField();
		carriageId.setBounds(192, 138, 141, 18);
		add(carriageId);

		transiterId = new JTextField();
		transiterId.setBounds(405, 138, 50, 18);
		add(transiterId);

		String[][] tableData = { { "1", "" } };
		String[] columnTitle = { "编号", "订单条形码号" };
		// 以二维数组和一维数组来创建一个JTable对象

		model = new DefaultTableModel(tableData, columnTitle);
		barId = new JTable(model);

		JScrollPane jsc = new JScrollPane(barId);
		jsc.setVisible(true);
		jsc.setBounds(137, 218, 329, 191);
		add(jsc);

		// 使得表格大小随订单信息的填入而改变
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int num = model.getRowCount();
				String temp = (String) model.getValueAt(num - 1, 1);
				if (temp != "") {
					String[] conponent = { Integer.toString(num + 1), "" };
					((DefaultTableModel) model).addRow(conponent);
				}
				repaint();
			}
		});

		/*
		 * 确定
		 */
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(388, 419, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((trainId.getText().equals("")) || (carriageId.getText().equals(""))
						|| (transiterId.getText().equals(""))) {
					warning.setText("尚未完成对必填项的填写");
					warning.setBounds(198, 490, 463 - 198, 30);
					warning.setFont(new Font("Dialog", 1, 15));
					warning.setForeground(Color.red);
					warning.setVisible(true);
					add(warning);
					repaint();
				} else {
					ArrayList<String> BarIdArray = new ArrayList<String>();
					for (int i = 0; i < barId.getRowCount() - 1; i++) {
						BarIdArray.add((String) barId.getValueAt(i, 1));
					}
					TransitSheetVO vo = new TransitSheetVO();
					// destinationId项不存在，用null写入
					TransitFlightInformation flightInf = new TransitFlightInformation(date.getTime(),
							departure.getSelectedItem().toString(), destination.getSelectedItem().toString(),
							transiterId.getText().toString(), BarIdArray);
					flightInf.setFlightId(trainId.getText());
					flightInf.setShelfId(carriageId.getText());
					vo.setTransportType(TransportType.TRAIN);
					vo.setTransitInformation(flightInf);
					ResultMessage result = deliverblservice.transitTrainReceipt(vo);
					// 成功
					if (result.getResult() == Result.SUCCESS) {

						warning.setText("提交成功");
						warning.setBounds(270, 490, 70, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						warning.setVisible(true);
						add(warning);

						fare = new JLabel();
						fare.setText(Double.toString(flightInf.getFare()) + "元");
						fare.setBounds(180, 192, 70, 30);
						fare.setForeground(Color.GRAY);
						fare.setFont(new Font("Dialog", 0, 18));
						fare.setVisible(true);
						add(fare);

						trainTransitId = new JLabel();
						trainTransitId.setText(flightInf.getFlightTransitId());
						trainTransitId.setBounds(290, 165, 140, 30);
						trainTransitId.setForeground(Color.GRAY);
						trainTransitId.setFont(new Font("Dialog", 0, 18));
						trainTransitId.setVisible(true);
						add(trainTransitId);

						repaint();
					} else {
						// 失败
						warning.setText(result.getMessage());
						warning.setBounds(138, 490, 463 - 138, 30);
						warning.setFont(new Font("Dialog", 1, 15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					}
				}
			}
		});

	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/transit/background/background04.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
