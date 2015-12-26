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
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControl;
import nju.sec.yz.ExpressSystem.presentation.controlerui.PositionControler;
import nju.sec.yz.ExpressSystem.vo.CarVO;

public class PositionCarModifyFillUi extends JPanel{
	private ClientControler mainControler;
	private PositionControler controler;
	private CarBlService carBl;
	private ButtonComponents bc;
	private CarVO carvo;
	
	private newJText JTcarId;
	private newJText JTcarCard;
	private newJText JTmechine;
	private newJText JTdipan;
	private DateChooser buyTime;
	private newJLabel workTime;
	private newJBut confirm;
	private newJBut back;
	private newJLabel warning;
	
	private static int carId_x=220;
	private static int carId_y=63;
	private static int carId_w=182;
	private static int carCard_x=215;
	private static int carCard_y=87;
	private static int carCard_w=182;
	private static int mechine_x=230;
	private static int mechine_y=115;
	private static int mechine_w=90;
	private static int dipan_x=200;
	private static int dipan_y=144;
	private static int dipan_w=90;
	private static int buyTime_x=218;
	private static int buyTime_y=168;
	private static int workTime_x=218;
	private static int workTime_y=190;
	private static int back_x=260;
	private static int back_y=232;
	private static int back_w=100;
	private static int back_h=24;
	private static int confirm_x=370;
	private static int confirm_y=232;
	private static int confirm_w=72;
	private static int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=488;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
//	private ImageIcon confirmIcon=new ImageIcon("graphic/position/button/button_confirm.png");
//	private ImageIcon backIcon=new ImageIcon("graphic/position/button/button_back.png");
	
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
//		bc.change();
		setLayout(null);
		setSize(490, 550);

		JTcarId=new newJText(carvo.getId());
		JTcarId.setBounds(carId_x, carId_y, carCard_w, h);
		add(JTcarId);
		
		JTcarCard=new newJText(carvo.getNumber());
		JTcarCard.setBounds(carCard_x, carCard_y, carCard_w, h);
		add(JTcarCard);
		
		JTmechine=new newJText(carvo.getMechine());
		JTmechine.setBounds(mechine_x, mechine_y, mechine_w, h);
		add(JTmechine);
		
		JTdipan=new newJText(carvo.getDipan());
		JTdipan.setBounds(dipan_x, dipan_y, dipan_w, h);
		add(JTdipan);
		
		//时间设定
		buyTime=new DateChooser(new Date(Integer.parseInt(carvo.getBuytime().substring(0, 4)),Integer.parseInt(carvo.getBuytime().substring(4,6)),Integer.parseInt(carvo.getBuytime().substring(6,8))),this, buyTime_x, buyTime_y);
		
		workTime=new newJLabel();
		workTime.setBounds(workTime_x, workTime_y, warning_w, warning_h);
		add(workTime);
		
		warning=new newJLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		back=new newJBut("返回原列表");
		back.setBounds(back_x,back_y,back_w,back_h);
		back.setMargin(new java.awt.Insets(0,0,0,0)); 
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				System.out.println("13");
				controler.positionChangePanel(PositionControl.CAR_MODIFY);
			}
		});
		add(back);
		
		confirm=new newJBut("确定");
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
