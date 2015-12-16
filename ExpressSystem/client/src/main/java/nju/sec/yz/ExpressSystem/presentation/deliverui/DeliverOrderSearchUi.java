package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class DeliverOrderSearchUi extends JPanel {
	private DeliverBlService deliver=new DeliverController();
	// 确定选项
	private JLabel confirmButton;
	private JTextField searchnumber;
	//提示信息
	private JLabel warning;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	private ArrayList<DeliverVO> dl;
	
	public DeliverOrderSearchUi(ClientControler controler) {
		initDeliverOrderSearch();
		DeliverButtonComponents bc=new DeliverButtonComponents(controler,this);
	}



	private void initDeliverOrderSearch() {
		setLayout(null);
		setSize(490, 550);
		
		name.add("订单条形码号");
		name.add("寄件人姓名");
		name.add("寄件地址");
		name.add("寄件城市");
		name.add("寄件单位");
		name.add("寄件人电话");
		name.add("寄件人手机号");
		name.add("收件人姓名");
		name.add("收件地址");
		name.add("收件城市");
		name.add("收件单位");
		name.add("收件人电话");
		name.add("收件人手机号");
		name.add("件数");
		name.add("重量");
		name.add("体积");
		name.add("品名");
		name.add("尺寸");
		
		table = new newTable(data, name, this, false);
		table.setBounds(144, 105, 315, 177);
		table.stopAutoRewidth();
		table.join();

		confirmButton = new JLabel();
		confirmButton.setBounds(441, 72, 23, 21);
		add(confirmButton);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SendSheetVO sheetvo=deliver.checkDeliverReceipt(searchnumber.getText());
//					快递单号填写错误的情况如何考虑
				if(sheetvo==null)
				{
					warning=new JLabel();
					warning.setText("输入信息错误");
					warning.setBounds(250,490,100,30);
					warning.setFont(new Font("Dialog",1,15));
					warning.setForeground(Color.red);
					add(warning);
					repaint();
				}else{
					changeData(sheetvo);
				}
			}
		});
		/*
		 * textfield
		 */
		searchnumber = new JTextField();
		searchnumber.setBounds(221, 72, 219, 20);
		add(searchnumber);
		
		setVisible(true);
	}
	private void changeData(SendSheetVO sheetvo)
	{
		SendInformation sendIn=sheetvo.getSendInformation();
		ToAndFromInformation fromPerson=sendIn.getFromPerson();
		ToAndFromInformation toPerson=sendIn.getToPerson();
		GoodInformation good=sendIn.getGood();
		
		Vector<String> vector=new Vector<String>();
		vector.add(sendIn.getBarId());
		vector.add(fromPerson.getName());
		vector.add(fromPerson.getAddress());
		vector.add(fromPerson.getCity());
		vector.add(fromPerson.getOrg());
		vector.add(fromPerson.getTelephone());
		vector.add(fromPerson.getCellphone());
		vector.add(toPerson.getName());
		vector.add(toPerson.getAddress());
		vector.add(toPerson.getCity());
		vector.add(toPerson.getOrg());
		vector.add(toPerson.getTelephone());
		vector.add(toPerson.getCellphone());
		vector.add(good.getTotal());
		vector.add(good.getWeight());
		vector.add(good.getVloume());
		vector.add(good.getName());
		vector.add(good.getSize()); 
		data.add(vector);
	}
	@Override
	public void paintComponent(Graphics g) {
		Image img01 = new ImageIcon("graphic/deliver/background/background03.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}



