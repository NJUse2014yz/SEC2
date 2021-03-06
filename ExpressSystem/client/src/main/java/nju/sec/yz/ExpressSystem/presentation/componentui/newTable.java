package nju.sec.yz.ExpressSystem.presentation.componentui;
/**
 * @author YU Fan
 * @usage 用来显示带滚动条的表格
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.util.Vector;
import java.util.function.UnaryOperator;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class newTable{
	private JPanel panel;
	
	private JTable table;
	private DefaultTableModel model;
	private boolean isMaker=false;//如果是制作时使用，设为true，可以扩展表格
	private newJScroll scroll;
	
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
	public newTable(Vector<Vector<String>> data,Vector<String> name,JPanel panel,boolean isMaker)
	{
		this.panel=panel;
		this.isMaker=isMaker;
		initialTable(data,name);
	}
	/**
	 * 刷新表格
	 * @param data String[][] 表格的新数据
	 */
	public void resetData()
	{
//		model.setDataVector(dataVector, columnIdentifiers);
//		table.getTableHeader().
		table.updateUI();
	}
	/**
	 * 停止自适应，使用滑动条
	 */
	public void stopAutoRewidth()
	{
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	/**
	 * 给需要用户填写的表格创建空的一行
	 * @param n int 列数
	 */
	public void initialBlank(int n)
	{
		model.addRow(createRow(n)); 
	}
	/**
	 * 给表格某列添加下拉框
	 * @param choose JComboBox 下拉框
	 * @param column int 在表格的第几列
	 */
	public void setJComboBox(newJCombo choose,int column)
	{
		table.getColumnModel().getColumn(column).setCellEditor(new DefaultCellEditor(choose));
	}
	/**
	 * 设置多选
	 */
	public void setSelectionMode(int n)
	{
		table.setSelectionMode(n);
	}
	/**
	 * 得到model
	 * @return table's model
	 */
	public DefaultTableModel getModel()
	{
		return this.model;
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
	 * 设置表格是否可见
	 * @param v boolean 可见为true
	 */
	public void setVisible(boolean v) {
		scroll.setVisible(v);
	}
	/**
	 * 为表格增加监听
	 * @param ma MouseAdapter 监听器
	 */
	public void addMouseListener(MouseAdapter ma)
	{
		table.addMouseListener(ma);
	}
	/**
	 * 得到被选择的行编号
	 * @return int 被选行编号
	 */
	public int getSelectedRow()
	{
		return table.getSelectedRow();
	}
	/**
	 * 得到表格的行数
	 * @return int 表格的行数
	 */
	public int getRowCount()
	{
		return table.getRowCount();
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
	 * 获得表格的选中行
	 * @return int[] 选中编号的数组
	 */
	public int[] getSelectedRows()
	{
		return table.getSelectedRows();
	}
	/**
	 * 设置表格是否可选
	 */
	public void setTableSelect()
	{
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
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
//	private void initialTable(final String[][] data,final String[] name)
//	{
	private void initialTable(final Vector<Vector<String>> data,final Vector<String> name)
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
						model.addRow(createRow(name.size())); 
						panel.repaint();
					}
				}
			});
		}
		table=new JTable();
		table.setModel(model);
		
		JTableHeader header=table.getTableHeader();
		header.setOpaque(false);
		header.getTable().setOpaque(false);
		header.setDefaultRenderer(new MyTableCellRenderer(2,Color.gray,18,Color.WHITE));  
//        TableCellRenderer headerRenderer= header.getDefaultRenderer();   
//        if (headerRenderer instanceof JLabel)   
//        {  
//            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);   
//            ((JLabel) headerRenderer).setOpaque(false);   
//        }  
		
		table.setOpaque(false);
		table.setDefaultRenderer(Object.class,new MyTableCellRenderer(0.3,new Color(97,96,96),15,new Color(172,173,173)));
		table.setRowHeight(20);
		
		scroll=new newJScroll(table);
//		scroll.setViewportView(table);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setColumnHeaderView(table.getTableHeader());//设置头部（HeaderView部分）  
        scroll.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明  
        
	}
	 
}
/**
 * @author YU Fan
 * @usage 表格的渲染器
 */
class MyTableCellRenderer extends DefaultTableCellRenderer
{
	private float lineSize=1;
	private int wordSize=15;
	private Color lineColor=Color.gray;
	private Color wordColor=Color.WHITE;
	private Color selectColor=Color.WHITE;
	private Font font=new Font("Microsoft YaHei",Font.PLAIN,15);
	
    public MyTableCellRenderer(double linesize,Color linecolor,int wordsize,Color wordcolor)
    {
    	super();
    	lineSize=(float)linesize;
    	wordSize=wordsize;
    	lineColor=linecolor;
    	wordColor=wordcolor;
    	font=new Font("Microsoft YaHei",Font.PLAIN,wordSize);
    	this.setOpaque(false);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,   
            boolean isSelected, boolean hasFocus, int row, int column)  
    {
		return drawCell(value,row == table.getSelectedRow());
    }
    private Component drawCell(Object value,boolean isSelected)
    {
		JLabel label = new JLabel() {
			protected void paintComponent(Graphics g) {
				// 重载jlabel的paintComponent方法，在这个jlabel里手动画线
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(lineSize));
				g2d.setColor(lineColor);
				g2d.drawLine(0, 0, this.getWidth(), 0);
				g2d.drawLine(0, this.getHeight() - 1, this.getWidth(),
						this.getHeight() - 1);
				g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
						this.getHeight() - 1);

				// 一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字
				super.paintComponent(g);
			}
		};
		label.setText(value != null ? value.toString() : "unknown");
		label.setFont(font);
		if (isSelected) {
			label.setForeground(selectColor);
		} else {
			label.setForeground(wordColor);
		}
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
    }
}