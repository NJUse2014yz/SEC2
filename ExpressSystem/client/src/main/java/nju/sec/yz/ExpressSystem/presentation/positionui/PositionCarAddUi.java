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

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.CarController;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.CarBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public class PositionCarAddUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	
	private CarBlService carBl;
	private JTextField JTcarId;
	private JTextField JTcarCard;
	private JTextField JTmechine;
	private JTextField JTdipan;
	private DateChooser buyTime;
	private JLabel workTime;
	private JButton confirm;
	private JLabel warning;
	private ButtonComponents bc;
	
	private static int carId_x=220;
	private static int carId_y=66;
	private static int carId_w=182;
	private static int carCard_x=252;
	private static int carCard_y=91;
	private static int carCard_w=182;
	private static int mechine_x=235;
	private static int mechine_y=119;
	private static int mechine_w=90;
	private static int dipan_x=206;
	private static int dipan_y=147;
	private static int dipan_w=90;
	private static int buyTime_x=218;
	private static int buyTime_y=174;
	private static int workTime_x=218;
	private static int workTime_y=195;
	private static int confirm_x=370;
	private static int confirm_y=232;
	private static int confirm_w=72;
	private static int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	
	private static int h=20;
	
	public PositionCarAddUi(ClientControler mainControler,ButtonComponents bc){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;		
		carBl=new CarController();
		initDeliverMainUi();

	}

	private void initDeliverMainUi() {

		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);

		JTcarId=new JTextField();
		JTcarId.setBounds(carId_x, carId_y, carCard_w, h);
		add(JTcarId);
		
		JTcarCard=new JTextField();
		JTcarCard.setBounds(carCard_x, carCard_y, carCard_w, h);
		add(JTcarCard);
		
		JTmechine=new JTextField();
		JTmechine.setBounds(mechine_x, mechine_y, mechine_w, h);
		add(JTmechine);
		
		JTdipan=new JTextField();
		JTdipan.setBounds(dipan_x, dipan_y, dipan_w, h);
		add(JTdipan);
		
		buyTime=new DateChooser(this, buyTime_x, buyTime_y);
		
		workTime=new JLabel();
		workTime.setFont(new Font("Dialog",1,10));
		workTime.setForeground(Color.WHITE);
		workTime.setBounds(workTime_x, workTime_y, warning_w, warning_h);
		add(workTime);

		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x,confirm_y,confirm_w,confirm_h);
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				
				if(JTcarId.getText().equals("")||JTcarCard.getText().equals("")||JTmechine.getText().equals("")||JTdipan.getText().equals(""))
				{
					warning.setText("有必填项未填");
					warning.setVisible(true);
					repaint();
				}
				else
				{
					CarVO carvo=new CarVO(JTcarId.getText(),JTcarCard.getText(),buyTime.getTime(),JTmechine.getText(),JTdipan.getText());
					ResultMessage result=carBl.add(carvo);
					String[] message=result.getMessage().split(" ");
					if(result.getResult()==Result.SUCCESS)
					{
						warning.setText("提交成功");
						warning.setVisible(true);
					}
					else{
						warning.setText(message[0]);
						warning.setVisible(true);
					}
					workTime.setText(message[1]+"年");
					workTime.setVisible(true);
					repaint();
				}
			}
		});
		add(confirm);
		
		setVisible(true);

		
	}

	
	
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/position/background/add_car_background.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
