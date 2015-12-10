package nju.sec.yz.ExpressSystem.presentation.componentui;
/**
 * @author YU Fan
 */
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class newTable{
	private JPanel panel;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private String[][] data={{""}};
	private String[] name={""};
	
	private int x;
	private int y;
	private int w;
	private int h;
	
	public newTable(String[][] data,String[] name,JPanel panel)
	{
		model=new DefaultTableModel(data,name);
		this.panel=panel;
		table=new JTable(model);
		scroll=new JScrollPane(table);
		scroll.setBounds(x, y, w, h);
		panel.add(scroll);
	}
	
	public void resetData(String[][] data)
	{
		panel.remove(scroll);
		model=new DefaultTableModel(data,name);
		table=new JTable(model);
		scroll.setBounds(x, y, w, h);
		panel.add(scroll);
	}
	
	public void setJComboBox(newJComboBox choose,int column)
	{
		this.getColumnModel().getColumn(column).setCellEditor(new DefaultCellEditor(choose));
	}
}
