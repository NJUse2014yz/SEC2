package nju.sec.yz.ExpressSystem.presentation.inventoryui;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import nju.sec.yz.ExpressSystem.bl.inventorybl.InventoryController;
import nju.sec.yz.ExpressSystem.blservice.inventoryBlService.InventoryBlService;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryObserve extends JPanel{

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;
	private String[] columnTitle = { "快递编号", "入库日期", "目的地", "区号", "排号", "架号", "位号" };

	private JButton confirm;
	private JLabel warning=new JLabel();
	private JLabel total=new JLabel();
	private JTable table;
	

	public InventoryObserve(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryObserve();
	}

	private void iniInventoryObserve() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);
		DateChooser date1=new DateChooser(this,214,60);
		DateChooser date2=new DateChooser(this,214,87);
		
		
		
		
		
		Object[][] tableData = {};
		
		table = new JTable(tableData, columnTitle);
		// 将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来
		JScrollPane jsc = new JScrollPane(table);
		jsc.setVisible(true);
		jsc.setBounds(140,117,319, 208);
		add(jsc);
		
		
		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(140+319-76, 87, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//失败
				warning.setText("数据有问题");
				warning.setBounds(138, 490, 463 - 138, 30);
				warning.setFont(new Font("Dialog", 1, 15));
				warning.setForeground(Color.red);
				add(warning);
				repaint();
				
				//成功
				InventoryListVO vo=inventoryservice.observeStock(null,date1.getTime(),date2.getTime());
				TableModel model=table.getModel();
				Object[][] newTableData={};
				model=new DefaultTableModel(newTableData,columnTitle);
				table=new JTable(model);
//需要得到一个当前数目的值				
//				total.setText();
				total.setBounds(229, 334, 62, 23);
				total.setForeground(Color.GRAY);
				total.setFont(new Font("Dialog", 0, 18));
				total.setVisible(true);
				add(total);
				repaint();
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background05.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
}
