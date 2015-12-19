package nju.sec.yz.ExpressSystem.presentation.positionui;

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

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.DeliveryInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;

public class PositionDeliveUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private DeliverBlService deliverBl;
	private ButtonComponents bc;

	private newJText barId;
	private DateChooser date;
	private newJText deliver;
	private newJBut confirm;
	private newJLabel warning=new newJLabel();
	
	private static final int bar_x=259;
	private static final int bar_y=66;
	private static final int bar_w=183;
	private static final int date_x=213;
	private static final int date_y=94;
	private static final int deliver_x=207;
	private static final int deliver_y=117;
	private static final int deliver_w=110;
	private static final int confirm_x=355;
	private static final int confirm_y=159;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int h=20;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
//	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	
	
		
	
	
	
	public PositionDeliveUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		deliverBl=new DeliverController();
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		barId=new newJText();
		barId.setBounds(bar_x, bar_y, bar_w, h);
		add(barId);
		
		date=new DateChooser(this, date_x, date_y);
		
		deliver=new newJText();
		deliver.setBounds(deliver_x, deliver_y, deliver_w, h);
		add(deliver);
		
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		add(warning);
		warning.setVisible(false);
		
		confirm=new newJBut("确定");
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				DeliverySheetVO sheet=new DeliverySheetVO();
				DeliveryInformation di=new DeliveryInformation();

				di.setOutDeliverId(deliver.getText());
				di.setTime(date.getTime());
				di.setBarId(barId.getText());
				
				sheet.setDeliveryInformation(di);
				sheet.setType(ReceiptType.POSITION_SEND_RECEIPT);
				
				if(barId.getText().equals("")||deliver.getText().equals(""))
				{
					warning.NotFilled();
				}
				else
				{
					ResultMessage result=deliverBl.positionSendReceipt(sheet);
					warning.Reply(result);
				}
				repaint();
			}
		});
		add(confirm);
		
		setVisible(true);
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/delive_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
