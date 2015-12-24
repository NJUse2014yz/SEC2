package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.AgencyBlService;
import nju.sec.yz.ExpressSystem.common.DeliveryType;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.PackType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class DeliverOrderInUi extends JPanel {

	DeliverBlService deliverBlService = new DeliverController();
	
	AgencyBlService agency=new ManagerController();

	// 确定选项
	private JButton confirmButton;

	// 寄件人信息
	private JTextField nameSender;
	private JTextField addressSender;
	private JTextField organizaionSender;
	private JTextField telephoneSender;
	private JTextField cellphoneSender;
//	private JTextField citySender;
	private JComboBox citySender;

	// 收件人信息
	private JTextField nameConsignee;
	private JTextField addressConsignee;
	private JTextField organizaionConsignee;
	private JTextField telephoneConsignee;
	private JTextField cellphoneConsignee;
//	private JTextField cityConsignee;
	private JComboBox cityConsignee;

	// 货物信息
	private JTextField nameGood;
	private JTextField totalGood;
	private JTextField weightGood;
	private JTextField vloumeGood;
	private JTextField sizeGood;

	// 订单条码号
	private JTextField barId;

	// 包装费，快递种类
	private static JComboBox packType;
	private static JComboBox deliveryType;

	// 提示信息
	private newJLabel warning = new newJLabel();
	//城市选项
	private String[] city;

	public DeliverOrderInUi(ClientControler controler) {

		initDeliverOrderIn();
		// 侧边栏功能选择项、退出系统、退出当前帐户
		DeliverButtonComponents bc = new DeliverButtonComponents(controler, this);
		
	}

	public void initDeliverOrderIn() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);

		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
//		confirmButton = new JButton(cinfirmIcon);
		confirmButton = new newJBut("确定");
		confirmButton.setBounds(378, 456, 76, 27);
		add(confirmButton);
		setVisible(true);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((addressSender.getText().equals("")) 
						|| (cellphoneSender.getText().equals(""))
//						|| (citySender.getText().equals("")) 
						|| (addressConsignee.getText().equals(""))
						|| (cellphoneConsignee.getText().equals(""))
//						|| (cityConsignee.getText().equals(""))
						|| (totalGood.getText().equals("")) 
						|| (weightGood.getText().equals(""))
						|| (vloumeGood.getText().equals("")) 
						|| (sizeGood.getText().equals(""))
						|| (barId.getText().equals(""))) {
					warning.NotFilled();
//					warning.setText("尚未完成对带*必填项的填写");
//					warning.setBounds(198, 490, 463 - 198, 30);
//					warning.setFont(new Font("Dialog", 1, 15));
//					warning.setForeground(Color.red);
//					warning.setVisible(true);
				} else {
					// translate data
					SendSheetVO sendsheet = new SendSheetVO();
					ToAndFromInformation fromPerson = new ToAndFromInformation(nameSender.getText(),
							citySender.getSelectedItem().toString(), addressSender.getText(), organizaionSender.getText(),
							telephoneSender.getText(), cellphoneSender.getText());
					ToAndFromInformation toPerson = new ToAndFromInformation(nameConsignee.getText(),
							cityConsignee.getSelectedItem().toString(), addressConsignee.getText(), organizaionConsignee.getText(),
							telephoneConsignee.getText(), cellphoneConsignee.getText());
					GoodInformation goodIn = new GoodInformation(totalGood.getText(), weightGood.getText(),
							vloumeGood.getText(), nameGood.getText(), sizeGood.getText());
					SendInformation sendIn = new SendInformation(barId.getText(), toPerson, fromPerson, goodIn,
							getdeliveryType(deliveryType), getpackType(packType));
					sendsheet.setSendInformation(sendIn);
					// 判断输入的信息是否正确
					ResultMessage result = deliverBlService.deliverReceipt(sendsheet);
					warning.Reply(result);
					// 失败
//					if (result.getResult() == Result.FAIL) {
//
//						warning.setText(result.getMessage());
//						warning.setBounds(138, 490, 463 - 138, 30);
//						warning.setFont(new Font("Dialog", 1, 15));
//						warning.setForeground(Color.red);
//					} else {
//						// 提交成功
//						warning.setText("提交成功");
//						warning.setBounds(270, 490, 70, 30);
//						warning.setFont(new Font("Dialog", 1, 15));
//						warning.setForeground(Color.red);
//						warning.setVisible(true);
					if (result.getResult() != Result.FAIL) {
					

						String temp = result.getMessage();

						double cost = Double.parseDouble(temp.substring(0, temp.indexOf(' ')));
						int time = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1, temp.length()));

						JLabel predictTime = new newJLabel();
						predictTime.setText(Integer.toString(time) + "天");
						predictTime.setBounds(305, 428, 70, 30);
//						predictTime.setForeground(Color.GRAY);
//						predictTime.setFont(new Font("Dialog", 0, 18));
//						predictTime.setVisible(true);
						add(predictTime);

						JLabel costForAll = new newJLabel();
						costForAll.setText(new DecimalFormat(".00").format(cost) + "元");
						costForAll.setBounds(193, 428, 70, 30);
//						costForAll.setForeground(Color.GRAY);
//						costForAll.setFont(new Font("Dialog", 0, 18));
//						costForAll.setVisible(true);
						add(costForAll);
						
						confirmButton.setEnabled(false);

					}
				}
				add(warning);
				repaint();
			}
		});

		/*
		 * textfield
		 */

		nameSender = new newJText();
		nameSender.setBounds(185, 84, 58, 18);
		add(nameSender);

		addressSender = new newJText();
		addressSender.setBounds(295, 87, 116, 15);
		add(addressSender);

		organizaionSender = new newJText();
		organizaionSender.setBounds(185, 113, 85, 15);
		add(organizaionSender);

		telephoneSender = new newJText();
		telephoneSender.setBounds(319, 113, 140, 15);
		add(telephoneSender);

		cellphoneSender = new newJText();
		cellphoneSender.setBounds(185, 140, 140, 15);
		add(cellphoneSender);

		
		//城市选项
		
		ArrayList<TransitVO> transits=agency.observeAllTransit();
		city=new String[transits.size()];
		
		for(int c=0;c<transits.size();c++){
			city[c]=transits.get(c).getLocation();
		}
		
		citySender = new newJCombo(city);
		citySender.setBounds(389, 135, 70, 20);
		add(citySender);

		cityConsignee =  new newJCombo(city);
		cityConsignee.setBounds(389, 244, 70, 20);
		add(cityConsignee);
		
		
		nameConsignee = new newJText();
		nameConsignee.setBounds(185, 198, 58, 15);
		add(nameConsignee);

		addressConsignee = new newJText();
		addressConsignee.setBounds(295, 198, 116, 15);
		add(addressConsignee);

		organizaionConsignee = new newJText();
		organizaionConsignee.setBounds(185, 224, 85, 15);
		add(organizaionConsignee);

		telephoneConsignee = new newJText();
		telephoneConsignee.setBounds(319, 224, 140, 15);
		add(telephoneConsignee);

		cellphoneConsignee = new newJText();
		cellphoneConsignee.setBounds(185, 249, 140, 15);
		add(cellphoneConsignee);


		totalGood = new newJText();
		totalGood.setBounds(185, 279, 58, 15);
		add(totalGood);

		weightGood = new newJText();
		weightGood.setBounds(330, 279, 58, 15);
		add(weightGood);

		vloumeGood = new newJText();
		vloumeGood.setBounds(185, 303, 58, 15);
		add(vloumeGood);

		nameGood = new newJText();
		nameGood.setBounds(332, 303, 58, 15);
		add(nameGood);

		sizeGood = new newJText();
		sizeGood.setBounds(185, 327, 85, 15);
		add(sizeGood);

		barId = new newJText();
		barId.setBounds(252, 354, 140, 15);
		add(barId);

		String[] pack={"纸箱","木箱","快递袋","其它"};
		packType = new newJCombo(pack);
		packType.setBounds(198, 378, 85, 20);
		add(packType);

		String[] delivery={"经济快递","标准快递","特快"};
		deliveryType = new newJCombo(delivery);
		deliveryType.setBounds(225, 407, 85, 20);
		add(deliveryType);
	}

	public static PackType getpackType(JComboBox packType){
		String type = packType.getSelectedItem().toString();
		

		if (type.equals("纸箱"))
			return PackType.PAPER;
		else if (type.equals("木箱"))
			return PackType.WOOD;
		else if (type.equals("快递袋"))
			return PackType.BAG;
		else if (type.equals("其他"))
			return PackType.OTHER;
		return null;

	}

	public static DeliveryType getdeliveryType(JComboBox deliveryType) {
		switch (deliveryType.getSelectedItem().toString()) {
		case "经济快递":
			return DeliveryType.ECONOMIC;
		case "标准快递":
			return DeliveryType.STANDARD;
		case "特快":
			return DeliveryType.FAST;
		default:
			return null;

		}
	}

	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/deliver/background/background02.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
