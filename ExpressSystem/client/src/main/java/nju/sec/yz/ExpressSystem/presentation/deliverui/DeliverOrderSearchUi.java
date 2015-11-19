package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.table.TableColumn;

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
						OrderInformation orderInformation=ordervo.getOrderInformation();
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
			
			
			
			
			
			
			
			
			
			
			
			
//			JScrollPane scp=new JScrollPane();
//			scp.setBounds(144,105,315,177);
//			add(scp);
//			Object[][] info={{"张三","阿萨德","到达单","11111111","11111111111","杭州"}};
//			
//			
//			String[] title={"寄件人姓名","住址","单位","电话","手机","城市"};
//			JTable tab=new JTable(info,title);
//			 tab.setPreferredScrollableViewportSize(new Dimension(200,30));
//			 tab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
//			 tab.setVisible(true);
//			JTableHeader jth=tab.getTableHeader();
//			scp.getViewport().add(tab);
			
			Object[][] p={
				      {"阿呆",new Integer(66),new Integer(32),new Integer(98),new Boolean(false),new Boolean(false)},
				      {"阿呆",new Integer(82),new Integer(69),new Integer(128),new Boolean(true),new Boolean(false)},
				    };
				    String[] n={"姓名","语文","数学","总分","及格","作弊"};
				    TableColumn column=null;
				    JTable table=new JTable(p,n);
				    table.setPreferredScrollableViewportSize(new Dimension(200,30));
				    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				    table.setVisible(true);
				    add(table);
				   
//				      JScrollPane scrollPane=new JScrollPane(table);
//				      add(scrollPane);
		}



		@Override
		public void paintComponent(Graphics g) {

			Image img01 = new ImageIcon("graphic/deliver/background/background03.png").getImage();

			g.drawImage(img01, 0, 0, 490, 550, null);

		}

		
		
}



