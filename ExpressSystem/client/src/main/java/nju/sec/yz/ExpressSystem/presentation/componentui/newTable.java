package nju.sec.yz.ExpressSystem.presentation.componentui;
/**
 * @author YU Fan
 * @usage 用来显示带滚动条的表格
 */
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class newTable{
	private JPanel panel;
	
	private JTable table;
	private DefaultTableModel model;
	private boolean isMaker=false;//如果是制作时使用，设为true，可以扩展表格
	private JScrollPane scroll;
	private String[][] data={{""}};
	private String[] name={""};
	
	private int x=0;
	private int y=0;
	private int w=0;
	private int h=0;
	
	/**
	 * @param data String[][] 表格数据
	 * @param name String[] 表头
	 * @param panel JPanel 所属Panel
	 * @param isMaker boolean 是自制表格时使用（不是直接显示内容）
	 */
	public newTable(String[][] data,String[] name,JPanel panel,boolean isMaker)
	{
		this.panel=panel;
		this.isMaker=isMaker;
		showTable(data,name);
	}
	/**
	 * 刷新表格
	 * @param data String[][] 表格的新数据
	 */
	public void resetData(String[][] data)
	{
		panel.remove(scroll);
		showTable(data,name);
	}
	/**
	 * 给表格某列添加下拉框
	 * @param choose JComboBox 下拉框
	 * @param column int 在表格的第几列
	 */
	public void setJComboBox(JComboBox choose,int column)
	{
		table.getColumnModel().getColumn(column).setCellEditor(new DefaultCellEditor(choose));
	}
	/**
	 * 设置表格的位置大小
	 * @param x int 表格的x坐标
	 * @param y int 表格的y坐标
	 * @param w int 表格的宽
	 * @param h int 表格的高
	 */
	public void setBounds(int x,int y,int w,int h)
	{
		scroll.setBounds(x, y, w, h);
	}
	/**
	 * 把表格加进panel
	 */
	public void join()
	{
		panel.add(scroll);
	}
	/**
	 * 获得某个位置的字符串
	 * @param r int 行
	 * @param c int 列
	 * @param isChoose boolean 是否为下拉框
	 * @return String r行c列内容
	 */
	public String getValueAt(int r,int c,boolean isChoose)
	{
		if(isChoose)
		{
			return (String) table.getCellEditor(r, c).getCellEditorValue();
		}
		else
		{
			return (String) model.getValueAt(r, c);
		}
	}
	/**
	 * 获得表格的行数
	 * @return int 表格的行数
	 */
	public int getRowNumber()
	{
		return table.getRowCount()-1;
	}
	
	/**
	 * 提供新创建的空字符串数组
	 * @param n int 需要的字符串数组长度
	 * @return String[] 新的所需长的的空字符串数组
	 */
	private String[] createRow(int n)
	{
		String[] blank=new String[n];
		for(int i=0;i<n;i++)
		{
			blank[i]="";
		}
		return blank;
	}
	/**
	 * 设置表格
	 * @param data String[][] 表格数据
	 * @param name String[] 表头
	 */
	private void showTable(final String[][] data,final String[] name)
	{
		model=new DefaultTableModel(data,name);
		if(isMaker)
		{
			//如果是自制表格，当在上一行有填写，并离开上一行时，添加空白的下一行
			model.addTableModelListener(new TableModelListener(){
				@Override
				public void tableChanged(TableModelEvent e) {
					int num=model.getRowCount();
					if(table.getSelectedRow()==num-1&&!((String) model.getValueAt(num-1, 0)).equals("")){
						model.addRow(createRow(name.length)); 
						panel.repaint();
					}
				}
			});
		}
		table=new JTable(model);
		table.setOpaque(false);
		table.setFont("Microsoft YaHei",);
		table.setRowHeight(20);
		scroll=new JScrollPane(table);
	}
}
