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
import nju.sec.yz.ExpressSystem.common.LoadInformation;
import nju.sec.yz.ExpressSystem.common.ReceiptType;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;

public class PositionDeliveUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private DeliverBlService deliverBl;
	private ButtonComponents bc;

	private JTextField barId;
	private DateChooser date;
	private JTextField deliver;
	private JButton confirm;
	private JLabel warning;
	
	private static final int bar_x=263;
	private static final int bar_y=69;
	private static final int bar_w=183;
	private static final int date_x=213;
	private static final int date_y=94;
	private static final int deliver_x=213;
	private static final int deliver_y=120;
	private static final int deliver_w=89;
	private static final int confirm_x=355;
	private static final int confirm_y=159;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int h=20;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	
	
		
	
	
	
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
		
		barId=new JTextField();
		barId.setBounds(bar_x, bar_y, bar_w, h);
		add(barId);
		
		date=new DateChooser(this, date_x, date_y);
		
		deliver=new JTextField();
		deliver.setBounds(deliver_x, deliver_y, deliver_w, h);
		add(deliver);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
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
					warning.setText("有必填项未填");
					warning.setVisible(true);
					repaint();
				}
				else
				{
					ResultMessage result=deliverBl.positionSendReceipt(sheet);
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
						warning.setVisible(true);
						repaint();
					}
					else{
						warning.setText(result.getMessage());
						warning.setVisible(true);
						repaint();
					}
				}
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
