package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ScrollPaneUI;

public class newJCombo extends JComboBox {

	// public newJCombo(String[] str){
	// for(int c=0;c<str.length;c++){
	// addItem(str[c]);
	// }
	// ComboBoxEditor editor=getEditor();
	// setBackground(new Color(70,70,70));
	// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,0));
	//
	// Font font = new Font("Microsoft YaHei",Font.PLAIN,15);
	// setFont(font);
	//
	// setForeground(Color.LIGHT_GRAY);
	//
	//
	// }
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
		setUI(new myScrollUI());
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
				renderer.setBackground(Color.WHITE);
			}
			list.setSelectionBackground(new Color(70,70,70));
			list.setBorder(null);
			renderer.setFont(new Font("Microsoft YaHei",Font.PLAIN,15));
			renderer.setHorizontalAlignment(JLabel.CENTER);
			return renderer;
		}
	}

	public class myScrollUI extends ScrollPaneUI {

		public myScrollUI() {
			super();
		}

		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
			int width = thumbBounds.width;
			int height = thumbBounds.height;
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.translate(thumbBounds.x, thumbBounds.y);
			g2.setColor(Color.BLUE);
			g2.drawRoundRect(1, 1, width - 2, height - 2, 5, 5);

			// g2.setColor(Color.ORANGE);
			// g2.drawLine(3,height/2,width-4,height/2);
			// g2.drawLine(3,height/2+3,width-4,height/2+3);
			// g2.translate(-thumbBounds.x, -thumbBounds.y);
		}

		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
			g.setColor(Color.PINK);
			int x = trackBounds.x;
			int y = trackBounds.y;
			int width = trackBounds.width;
			int height = trackBounds.height;
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));

			g2.fill3DRect(x, y, width, height, true);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2.setColor(Color.GREEN);
			g2.fill3DRect(x, y, 1, height + 1, true);
			// if(trackHighlight == DECREASE_HIGHLIGHT) {
			// paintDecreaseHighlight(g);
			// }
			// else if(trackHighlight == INCREASE_HIGHLIGHT) {
			// paintIncreaseHighlight(g);
			// }
		}

	}
}
