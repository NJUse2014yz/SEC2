package nju.sec.yz.ExpressSystem.presentation.deliverui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.deliverbl.DeliverController;
import nju.sec.yz.ExpressSystem.blservice.deliverBlService.DeliverBlService;
import nju.sec.yz.ExpressSystem.common.GoodInformation;
import nju.sec.yz.ExpressSystem.common.SendInformation;
import nju.sec.yz.ExpressSystem.common.ToAndFromInformation;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;

public class DeliverOrderSearchUi extends JPanel {
	
	DeliverBlService deliver=new DeliverController();
	
		// 确定选项
		private JLabel confirmButton;
		
		
		private JTextField searchnumber;

		//提示信息
		private JLabel warning=new newJLabel();
		
		private Object[][] TableData={};
		private String[] columnTitle = {"订单条形码号",
	    		"寄件人姓名" , "寄件地址" , "寄件城市",
	    		"寄件单位","寄件人电话","寄件人手机号",
	    		"收件人姓名","收件地址","收件城市",
	    		"收件单位","收件人电话","收件人手机号",
	    		"件数","重量","体积",
	    		"品名","尺寸"
	    		}; 
		
		JTable table;  
		TableModel model;
		
		public DeliverOrderSearchUi(ClientControler controler) {
			initDeliverOrderSearch();
			DeliverButtonComponents bc=new DeliverButtonComponents(controler,this);
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
			
			
			//以二维数组和一维数组来创建一个JTable对象  
		      table = new JTable(TableData , columnTitle);  
		      for(int c=0;c<18;c++){
		    	  table.getColumnModel().getColumn(c).setMinWidth(80);
		      }
		      
		      
		      //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
		      JScrollPane jsc=new JScrollPane(table);  
		   
		      jsc.setVisible(false);
		      jsc.setBounds(144,105,315,177);
		      add(jsc);
		      repaint();
		
			
			
			
			
			confirmButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					SendSheetVO sheetvo=deliver.checkDeliverReceipt(searchnumber.getText());
					
					
//					快递单号填写错误的情况如何考虑
					if(sheetvo==null){
						warning.setText("输入信息错误");
						warning.setForeground(Color.red);
						warning.setBounds(250,490,100,30);
						add(warning);
						table.setVisible(false);
						jsc.setVisible(false);
						repaint();
					}else{
						SendInformation sendIn=sheetvo.getSendInformation();
						ToAndFromInformation fromPerson=sendIn.getFromPerson();
						ToAndFromInformation toPerson=sendIn.getToPerson();
						GoodInformation good=sendIn.getGood();
						
						   TableData = new Object[1][18];  
						    TableData[0][0]=sendIn.getBarId();
						    TableData[0][1]=fromPerson.getName();
						    TableData[0][2]=fromPerson.getAddress();
						    TableData[0][3]=fromPerson.getCity();
						    TableData[0][4]=fromPerson.getOrg();
						    TableData[0][5]=fromPerson.getTelephone();
						    TableData[0][6]=fromPerson.getCellphone();
						    TableData[0][7]=toPerson.getName();
						    TableData[0][8]=toPerson.getAddress();
						    TableData[0][9]=toPerson.getCity();
						    TableData[0][10]=toPerson.getOrg();
						    TableData[0][11]=toPerson.getTelephone();
						    TableData[0][12]=toPerson.getCellphone();
						    TableData[0][13]=good.getTotal();
						    TableData[0][14]=good.getWeight();
						    TableData[0][15]=good.getVloume();
						    TableData[0][16]=good.getName();
						    TableData[0][17]=good.getSize();
						    
						    model=new DefaultTableModel(TableData,columnTitle);
							table.setModel(model);
							for(int i=0;i<table.getColumnCount();i++){
						    	  table.getColumnModel().getColumn(i).setMinWidth(80);
						      }
							table.setVisible(true);
							jsc.setVisible(true);
					}
					
					
				}
				});

			
			/*
			 * textfield
			 */

			searchnumber = new JTextField();
			searchnumber.setBounds(221, 72, 219, 20);
			searchnumber.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
			add(searchnumber);
		}

		@Override
		public void paintComponent(Graphics g) {

			Image img01 = new ImageIcon("graphic/deliver/background/background03.png").getImage();

			g.drawImage(img01, 0, 0, 490, 550, null);

		}

}



