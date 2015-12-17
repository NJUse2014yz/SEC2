package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

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
import nju.sec.yz.ExpressSystem.common.InventoryInInformation;
import nju.sec.yz.ExpressSystem.presentation.DateChooser;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.InventoryInSheetVO;
import nju.sec.yz.ExpressSystem.vo.InventoryListVO;

public class InventoryObserve extends JPanel{

private InventoryBlService inventoryservice=new InventoryController();
	
	private ClientControler maincontroler;

	private JButton confirm;
	private JLabel warning;
	private JLabel total;
	private newTable table;
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private Vector<String> name=new Vector<String>();
	private ArrayList<InventoryInSheetVO> involist;
	
	private DateChooser date1;
	private DateChooser date2;
	
	ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
	InventoryButtonComponents ibc=new InventoryButtonComponents(maincontroler,this);

	public InventoryObserve(ClientControler maincontroler){
		this.maincontroler=maincontroler;
		iniInventoryObserve();
	}

	private void iniInventoryObserve() {
		// TODO Auto-generated method stub
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name.add("快递编号");
		name.add("入库日期");
		name.add("目的地");
		name.add("区号");
		name.add("排号");
		name.add("架号");
		name.add("位号");
		
		date1=new DateChooser(this,214,60);
		date2=new DateChooser(this,214,87);
		
		table = new newTable(data,name,this,false);
		table.setBounds(140,117,319, 208);
		table.join();
		
		total=new JLabel();
		total.setBounds(229, 334, 62, 23);
		total.setForeground(Color.GRAY);
		total.setFont(new Font("Dialog", 0, 18));
		add(total);
		
		warning=new JLabel();
		warning.setBounds(138, 490, 463 - 138, 30);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		warning.setVisible(false);
		add(warning);
		
		confirm = new JButton(cinfirmIcon);
		confirm.setBounds(140+319-76, 87, 76, 27);
		add(confirm);
		setVisible(true);

		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int daystart = Integer.parseInt(date1.getTime());
				int dayend = Integer.parseInt(date2.getTime());

				if (daystart > dayend) {
					// 失败
					warning.setText("数据有问题");
					warning.setVisible(true);
					repaint();
				} else {
					// 成功
					InventoryListVO vo = inventoryservice.observeStock(
							date1.getTime(), date2.getTime());
					involist = (ArrayList<InventoryInSheetVO>) inventoryservice
							.checkStock().inList;
					changeData(involist);
					table.resetData();
					// 需要得到一个当前数目的值
					total.setText(Integer.toString(involist.size()));
					total.setVisible(true);
					repaint();
				}
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		Image img01=new ImageIcon("graphic/inventory/background/background05.png").getImage();
		g.drawImage(img01, 0, 0, 490, 550, null);
	}
	private void changeData(ArrayList<InventoryInSheetVO> involist)
	{
		for(int i=0;i<involist.size();i++){
			InventoryInInformation temp=involist.get(i).getInventoryInInformation();
			Vector<String> vector=new Vector<String>();
			vector.add(involist.get(i).getBarId());
			vector.add(temp.getTime());
			vector.add(temp.getDestination());
			vector.add(Integer.toString(temp.getBlock()));
			vector.add(Integer.toString(temp.getPositon()));
			vector.add(involist.get(i).getBarId());
			vector.add(involist.get(i).getBarId());
			data.add(vector);
		}
	}
}
