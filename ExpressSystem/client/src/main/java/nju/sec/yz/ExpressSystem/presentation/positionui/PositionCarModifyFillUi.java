package nju.sec.yz.ExpressSystem.presentation.positionui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

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
import nju.sec.yz.ExpressSystem.presentation.controlerui.POSITION_CONTROL;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public class PositionCarModifyFillUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private CarBlService carBl;
	private ButtonComponents bc;
	private CarVO carvo;
	
	private JTextField JTcarId;
	private JTextField JTcarCard;
	private JTextField JTmechine;
	private JTextField JTdipan;
	private DateChooser buyTime;
	private JLabel workTime;
	private JButton confirm;
	private JButton back;
	private JLabel warning;
	
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
	private static int back_x=270;
	private static int back_y=232;
	private static int back_w=90;
	private static int back_h=20;
	private static int confirm_x=370;
	private static int confirm_y=232;
	private static int confirm_w=72;
	private static int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
	private ImageIcon backIcon=new ImageIcon("graphic/position/button/button_back.png");
	
	private static int h=20;
	
	
	public PositionCarModifyFillUi(ClientControler mainControler,ButtonComponents bc,String id){
		super();
		this.mainControler=mainControler;
		controler=mainControler.positionControler;
		this.bc=bc;
		carBl=new CarController();
		carvo=carBl.getSingle(id);
		initDeliverMainUi();
	}

	private void initDeliverMainUi() {
		
		bc.changePanel(this);
		bc.change();
		setLayout(null);
		setSize(490, 550);

		JTcarId=new JTextField();
		JTcarId.setBounds(carId_x, carId_y, carCard_w, h);
		JTcarId.setText(carvo.getId());
		add(JTcarId);
		
		JTcarCard=new JTextField();
		JTcarCard.setBounds(carCard_x, carCard_y, carCard_w, h);
		JTcarCard.setText(carvo.getNumber());
		add(JTcarCard);
		
		JTmechine=new JTextField();
		JTmechine.setBounds(mechine_x, mechine_y, mechine_w, h);
		JTmechine.setText(carvo.getMechine());
		add(JTmechine);
		
		JTdipan=new JTextField();
		JTdipan.setBounds(dipan_x, dipan_y, dipan_w, h);
		JTdipan.setText(carvo.getDipan());
		add(JTdipan);
		
		//时间设定
		buyTime=new DateChooser(new Date(Integer.parseInt(carvo.getBuytime().substring(0, 4)),Integer.parseInt(carvo.getBuytime().substring(4,6)),Integer.parseInt(carvo.getBuytime().substring(6,8))),this, buyTime_x, buyTime_y);
		
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
		
		back=new JButton(backIcon);
		back.setBounds(back_x,back_y,back_w,back_h);
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				controler.positionChangePanel(POSITION_CONTROL.CAR_MODIFY);
			}
		});
		
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
					ResultMessage result=carBl.modify(carvo);
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

		Image img01 = new ImageIcon("graphic/position/background/modify_car_background2.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
