package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJScroll.myScrollBarUI;

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
			list.setSelectionBackground(new Color(70, 70, 70));
			list.setBorder(null);
			renderer.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
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
			arrow.setIcon(XUtil.defaultComboBoxArrowIcon);
			arrow.setRolloverEnabled(true);
			arrow.setRolloverIcon(XUtil.defaultComboBoxArrowIcon_Into);
			arrow.setBorder(null);
			arrow.setBackground(XUtil.defaultComboBoxColor);
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
				arrow.setIcon(XUtil.defaultComboBoxArrowIcon_Into);
			} else {
				heightOffset = 0;
				height = (int) this.getPreferredSize(c).getHeight() - 1;
				arrow.setIcon(XUtil.defaultComboBoxArrowIcon);
			}
			if (comboBox.isFocusable()) {
				g2.setColor(new Color(150, 207, 254));
			}
			g2.drawRoundRect(0, 0, width, height + heightOffset, ARCWIDTH, ARCHEIGHT);
		}

		public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
			Font oldFont = comboBox.getFont();
			comboBox.setFont(XUtil.defaultComboBoxFont);

			super.paintCurrentValue(g, bounds, hasFocus);
			comboBox.setFont(oldFont);
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
					newJScroll sp = new newJScroll(list
//							, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
							);
					sp.setHorizontalScrollBar(null);
					return sp;
				}

				// 重载paintBorder方法 来画出我们想要的边框..
				public void paintBorder(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(new Color(150, 207, 254));
					g2.drawRoundRect(0, -arrow.getHeight(), getWidth() - 1, getHeight() + arrow.getHeight() - 1, 0, 0);
				}
			};
			return popup;
		}
	}

//	public class myScrollUI extends BasicScrollBarUI {
//
//		private static final float ARC_NUMBER = 20.0f;
//
////		public myScrollUI() {
////			super();
////		}
//
//		@Override
//		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
//
//			Graphics2D g2 = (Graphics2D) g;
//			int w = thumbBounds.width - 1;
//			int h = thumbBounds.height - 1;
//			Paint oldPaint = g2.getPaint();
//
//			// 消除锯齿状边缘
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//			g2.translate(thumbBounds.x, thumbBounds.y);
//
//			Shape arcRect = new RoundRectangle2D.Float(0.0f, 0.0f, (float) w, (float) h, ARC_NUMBER, ARC_NUMBER);
//			// 填充滚动条矩形
//			Paint arcRectPaint = null;
//			if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
//				arcRectPaint = new GradientPaint(0, 0, new Color(225, 225, 225), thumbBounds.width, 0,
//						new Color(162, 162, 162));
//			} else {
//				arcRectPaint = new GradientPaint(0, 0, new Color(225, 225, 225), 0, thumbBounds.height,
//						new Color(162, 162, 162));
//			}
//			g2.setPaint(arcRectPaint);
//			g2.fill(arcRect);
//			// 画滚动条矩形边框
//			// g2.setColor(new Color(70, 70, 70));
//			// g2.draw(arcRect);
//			// 画滚动条矩形内圈边框
//			// g2.setColor(new Color(230, 230, 230));
//			// Rectangle bounds = arcRect.getBounds();
//			// g2.drawRoundRect(bounds.x + 1, bounds.y + 1, bounds.width - 2,
//			// bounds.height - 2, (int) ARC_NUMBER, (int) ARC_NUMBER);
//			//
//			// g2.translate(-thumbBounds.x, -thumbBounds.y);
//			// g2.setPaint(oldPaint);
//		}
//
//		@Override
//		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
//			Graphics2D g2 = (Graphics2D) g;
//			Paint foregroundRectPaint = null;
//			Paint backgroupRectPaint = null;
//			Paint oldPaint = g2.getPaint();
//			// 绘制滚动背景
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
//				foregroundRectPaint = new Color(70, 70, 70);
//				backgroupRectPaint = new Color(70, 70, 70);
//			} else {
//				foregroundRectPaint = new Color(70, 70, 70);
//				backgroupRectPaint = new Color(70, 70, 70);
//			}
//			g2.setPaint(backgroupRectPaint);
//			g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
//			g2.setPaint(foregroundRectPaint);
//			g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1,
//					(int) ARC_NUMBER, (int) ARC_NUMBER);
//			g2.setColor(new Color(70, 70, 70));
//			g2.drawRoundRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1,
//					(int) ARC_NUMBER, (int) ARC_NUMBER);
//
//			g2.setPaint(oldPaint);
//
//			// 始终没有进入这两个判断方法，通过单独调用它们中的任意一个即刻明白这两个方法的含义
//			if (trackHighlight == DECREASE_HIGHLIGHT) {
//				paintDecreaseHighlight(g);
//			} else if (trackHighlight == INCREASE_HIGHLIGHT) {
//				paintIncreaseHighlight(g);
//			}
//		}
//
//		@Override
//		protected void paintDecreaseHighlight(Graphics g) {
//		}
//
//		@Override
//		protected void paintIncreaseHighlight(Graphics g) {
//		}
//
//		@Override
//		protected javax.swing.JButton createDecreaseButton(int orientation) {
//			return new BasicArrowButton(orientation) {
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see javax.swing.plaf.basic.BasicArrowButton#paint(java.awt.
//				 * Graphics)
//				 */
//				@Override
//				public void paint(Graphics g) {
//					GradientPaint backgroupRectPaint = null;
//					// if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
//					// backgroupRectPaint = new GradientPaint(0, 0, new
//					// Color(216, 216, 216),
//					// getWidth(), 0, new Color(172, 172, 172));
//					// } else {
//					// backgroupRectPaint = new GradientPaint(0, 0, new
//					// Color(216, 216, 216),
//					// 0, getHeight(), new Color(172, 172, 172));
//					// }
//					Graphics2D g2 = (Graphics2D) g;
//					g2.setPaint(new Color(70, 70, 70));
//					g2.fillRect(0, 0, getWidth(), getHeight());
//					// Draw the arrow
//					// IScrollBarUI.this.paintTriangle(g2, getSize(),
//					// direction);
//				}
//			};
//		}
//
//		@Override
//		protected javax.swing.JButton createIncreaseButton(int orientation) {
//			return new BasicArrowButton(orientation) {
//
//				/*
//				 * (non-Javadoc)
//				 * 
//				 * @see javax.swing.plaf.basic.BasicArrowButton#paint(java.awt.
//				 * Graphics)
//				 */
//				@Override
//				public void paint(Graphics g) {
//					GradientPaint backgroupRectPaint = null;
//					// if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
//					// backgroupRectPaint = new GradientPaint(0, 0, new
//					// Color(216, 216, 216),
//					// getWidth(), 0, new Color(172, 172, 172));
//					// } else {
//					// backgroupRectPaint = new GradientPaint(0, 0, new
//					// Color(216, 216, 216),
//					// 0, getHeight(), new Color(172, 172, 172));
//					// }
//					Graphics2D g2 = (Graphics2D) g;
//					g2.setPaint(new Color(70, 70, 70));
//					g2.fillRect(0, 0, getWidth(), getHeight());
//					// Draw the arrow
//					// IScrollBarUI.this.paintTriangle(g2, getSize(),
//					// direction);
//				}
//			};
//		}
//
//	}



	
}
