package nju.sec.yz.ExpressSystem.presentation.deliverui;

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
import javax.swing.table.JTableHeader;

import nju.sec.yz.ExpressSystem.bl.deliverbl.Deliver;
import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.OrderInformation;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.OrderVO;

public class DeliverOrderSearchUi extends JPanel {
	
	DeliverBlService deliver=new DeliverController();
	
		// 确定选项
		private JLabel confirmButton;
		
		
		private JTextField searchnumber;

		//提示信息
		private JLabel warning;
		
		
		
		public DeliverOrderSearchUi(ClientControler controler) {
			initDeliverOrderSearch();
			ButtonComponents bc=new ButtonComponents(controler,this);
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
					OrderVO ordervo=deliver.checkDeliver(searchnumber.getText());
					OrderInformation orderInformation=ordervo.getOrderInformation();
					
//					快递单号填写错误的情况如何考虑
					if(orderInformation==null){
						warning=new JLabel();
						warning.setText("输入信息错误");
						warning.setBounds(250,490,100,30);
						warning.setFont(new Font("Dialog",1,15));
						warning.setForeground(Color.red);
						add(warning);
						repaint();
					}else{
						
						JScrollPane scp=new JScrollPane();
						scp.setBounds(144,105,315,177);
						add(scp);
						ArrayList<String> temp=orderInformation.getTrail();
						int count=21+temp.size();
						Object[][] info=new Object[count][2];
						count=0;
						
						
						String[] title={"寄件人姓名","住址","单位","电话","手机"};
						JTable tab=new JTable(info,null);
						JTableHeader jth=tab.getTableHeader();
						scp.getViewport().add(tab);
						//依次输出快递信息
						
					}
					
					
					
				}
				});
			
			

			
			/*
			 * textfield
			 */

			searchnumber = new JTextField();
			searchnumber.setBounds(221, 72, 219, 20);
			add(searchnumber);
			
			
			
			
			
			
			
			
			
			
//			
//			
//			JScrollPane scp=new JScrollPane();
//			scp.setBounds(144,105,315,177);
//			add(scp);
//			Object[][] info=new Object[12][2];
//			
//			
//			String[] title={"寄件人姓名","住址","单位","电话","手机"};
//			JTable tab=new JTable(info,null);
//			JTableHeader jth=tab.getTableHeader();
//			scp.getViewport().add(tab);
			
			
			
		}



		@Override
		public void paintComponent(Graphics g) {

			Image img01 = new ImageIcon("graphic/deliver/background/background03.png").getImage();

			g.drawImage(img01, 0, 0, 490, 550, null);

		}

		
		
}



