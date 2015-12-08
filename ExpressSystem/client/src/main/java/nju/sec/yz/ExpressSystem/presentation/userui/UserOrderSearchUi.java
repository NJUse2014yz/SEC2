package nju.sec.yz.ExpressSystem.presentation.userui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import nju.sec.yz.ExpressSystem.bl.deliverbl.Deliver;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MAIN_CONTROL;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

public class UserOrderSearchUi extends JPanel {
	ClientControler mainControler;
	DeliverBlService deliver=new DeliverController();
		// 确定选项
		private JLabel confirmButton;
		private JTextField searchnumber;
		//提示信息
		private JLabel warning;
		// 退出系统
		private JButton exitButton;
		//退出当前帐户
		private JLabel leaveButton;
		
		private String id;
		private DeliverVO ordervo;
		
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		
		public UserOrderSearchUi(ClientControler controler,String id) {
			mainControler=controler;
			this.id=id;
			ordervo=deliver.checkDeliver(id);
			showtable();
			initDeliverOrderSearch();
		}
		private void initDeliverOrderSearch() {
			setLayout(null);
			setSize(490, 550);
			setVisible(true);

			/*
			 * 确定
			 */
			confirmButton = new JLabel();
			confirmButton.setBounds(441, 72, 23, 21);
			add(confirmButton);

			confirmButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					DeliverVO ordervo=deliver.checkDeliver(searchnumber.getText());
					
					
//					快递单号填写错误的情况如何考虑
					if(ordervo==null){
						warning=new JLabel();
						warning.setText("输入信息错误");
						warning.setBounds(250,490,100,30);
						warning.setFont(new Font("Dialog",1,15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					}else{
						showtable();
					}
				}
				});
			
			/*
			 * textfield
			 */

			searchnumber = new JTextField();
			searchnumber.setBounds(221, 72, 219, 20);
			add(searchnumber);
			
			//离开当前账户
			leaveButton=new JLabel();
			leaveButton.setBounds(433, 21, 37, 20);
			add(leaveButton);			
			
			exitButton= new JButton(ExitIcon);
			exitButton.setOpaque(false);
			exitButton.setBorder(null);
			exitButton.setContentAreaFilled(false); 
			exitButton.setBounds(490-19,0,19,19);
			add(exitButton);
			
			exitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			
			
			leaveButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					mainControler.mainChangePanel(MAIN_CONTROL.LOGIN);
				}
			});
			
		}
		@Override
		public void paintComponent(Graphics g) {

			Image img01 = new ImageIcon("graphic/adminstrater/background/ordersearch_background.png").getImage();

			g.drawImage(img01, 0, 0, 490, 550, null);

		}
		private void showtable(){
			List<String> trails=ordervo.trails;
//			SendInformation sendIn=orderInformation.getSendInformation();
//			ToAndFromInformation fromPerson=sendIn.getFromPerson();
//			ToAndFromInformation toPerson=sendIn.getToPerson();
//			GoodInformation good=sendIn.getGood();
			JTable table;  
//			   Object[][] tableData =   
//			    { 	{sendIn.getBarId()},
//			    		{fromPerson.getName()},
//			    		{fromPerson.getAddress()},
//			    		{fromPerson.getCity()},
//			    		{fromPerson.getOrg()},
//			    		{fromPerson.getTelephone()},
//			    		{fromPerson.getCellphone()},
//			    		{toPerson.getName()},
//			    		{toPerson.getAddress()},
//			    		{toPerson.getCity()},
//			    		{toPerson.getOrg()},
//			    		{toPerson.getTelephone()},
//			    		{toPerson.getCellphone()},
//			    		{good.getTotal()},
//			    		{good.getWeight()},
//			    		{good.getVloume()},
//			    		{good.getName()},
//			    		{good.getSize()}	
			       
//			   }; 
			String[][] tableData=new String[trails.size()][2];
			String[] temp;
			for(int i=0;i<trails.size();i++)
			{
				temp=trails.get(i).split(" ");
				tableData[i][0]=temp[0];
				tableData[i][1]=temp[1];
			}
//		    Object[] columnTitle = {"订单条形码号",
//		    		"寄件人姓名" , "寄件地址" , "寄件城市",
//		    		"寄件单位","寄件人电话","寄件人手机号",
//		    		"收件人姓名","收件地址","收件城市",
//		    		"收件单位","收件人电话","收件人手机号",
//		    		"件数","重量","体积",
//		    		"品名","尺寸"
//		    		};  
			String[] columnTitle={"物流轨迹","时间"};
			      //以二维数组和一维数组来创建一个JTable对象  
			      table = new JTable(tableData , columnTitle);  
			      //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
			      JScrollPane jsc=new JScrollPane(table);  
			      jsc.setVisible(true);
			      jsc.setBounds(144,105,315,177);
			      add(jsc);
			      repaint();
		
		}
}



