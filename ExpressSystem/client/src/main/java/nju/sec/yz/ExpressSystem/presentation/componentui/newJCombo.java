package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class newJCombo extends JComboBox {

	
	public newJCombo() {
		super();
		init();
	}

	public newJCombo(ComboBoxModel model) {
		super(model);
		init();
	}

	public newJCombo(Object[] items) {
		super(items);
		init();
	}

	public newJCombo(Vector<?> items) {
		super(items);
		init();
	}

	private void init() {
		setOpaque(false);
		setRenderer(new myComboBoxRenderer());
		setUI(new myComboBoxUI());

	}

	public class myComboBoxRenderer implements ListCellRenderer {

		private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

		public myComboBoxRenderer() {
			super();
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			JLabel renderer = (JLabel) defaultCellRenderer.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			if (isSelected) {
				renderer.setBackground(Color.BLACK);
				renderer.setForeground(Color.LIGHT_GRAY);
			} else {
				renderer.setBackground(new Color(70,70,70));
				renderer.setForeground(Color.WHITE);
			}
			list.setSelectionBackground(Color.LIGHT_GRAY);
			list.setSelectionForeground(Color.BLACK);
			
			list.setBorder(null);
			renderer.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
			renderer.setHorizontalAlignment(JLabel.CENTER);
			return renderer;
		}
	}

	public class myComboBoxUI extends BasicComboBoxUI {
		private JButton arrow;
		private boolean boundsLight = false;
		private static final int ARCWIDTH = 15;
		private static final int ARCHEIGHT = 15;

		public myComboBoxUI() {
			super();
		}

		protected JButton createArrowButton() {
			arrow = new JButton();
			arrow.setIcon(new ImageIcon("graphic/common/arrow.png"));
			arrow.setRolloverEnabled(true);
			arrow.setRolloverIcon(new ImageIcon("graphic/common/arrow.png"));
			arrow.setBorder(null);
			arrow.setBackground(new Color(70,70,70));
			arrow.setOpaque(false);
			arrow.setContentAreaFilled(false);
			return arrow;
		}

		public void paint(Graphics g, JComponent c) {
			hasFocus = comboBox.hasFocus();
			Graphics2D g2 = (Graphics2D) g;
			if (!comboBox.isEditable()) {
				Rectangle r = rectangleForCurrentValue();
				// 重点:JComboBox的textfield 的绘制 并不是靠Renderer来控制 这点让我很吃惊.
				// 它会通过paintCurrentValueBackground来绘制背景
				// 然后通过paintCurrentValue();去绘制JComboBox里显示的值
				paintCurrentValueBackground(g2, r, hasFocus);
				paintCurrentValue(g2, r, hasFocus);
			}
			

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int width = (int) this.getPreferredSize(c).getWidth() + arrow.getWidth() - 2;
			int height = 0;
			int heightOffset = 0;
			if (comboBox.isPopupVisible()) {
				heightOffset = 5;
				height = (int) this.getPreferredSize(c).getHeight();
				arrow.setIcon(new ImageIcon("graphic/common/arrow.png"));
			} else {
				heightOffset = 0;
				height = (int) this.getPreferredSize(c).getHeight() - 1;
				arrow.setIcon(new ImageIcon("graphic/common/arrow.png"));
			}
			if (comboBox.isFocusable()) {
//				g2.setColor(new Color(150, 207, 254));
			}
			g2.drawArc(0, 0, width, height + heightOffset, ARCWIDTH, ARCHEIGHT);
		}

		public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
			Font oldFont = comboBox.getFont();
			comboBox.setFont(new Font("Microsoft YaHei",Font.PLAIN,14));
			comboBox.setForeground(Color.BLACK);
			
			super.paintCurrentValue(g, bounds, hasFocus);
			comboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
			
		}
		
		public Dimension getPreferredSize(JComponent c) {
			return super.getPreferredSize(c);
		}

		public boolean isBoundsLight() {
			return boundsLight;
		}

		public void setBoundsLight(boolean boundsLight) {
			this.boundsLight = boundsLight;
		}

		protected ComboPopup createPopup() {
			ComboPopup popup = new BasicComboPopup(comboBox) {
				protected JScrollPane createScroller() {
					newJScroll sp = new newJScroll(list);
					sp.setHorizontalScrollBar(null);
					return sp;
				}

				// 重载paintBorder方法 来画出我们想要的边框..
				public void paintBorder(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(Color.LIGHT_GRAY);
					g2.drawRoundRect(0, -arrow.getHeight(), getWidth() - 1, getHeight() + arrow.getHeight() - 1, 0, 0);
				}
			};
			return popup;
		}
	}


	
}
