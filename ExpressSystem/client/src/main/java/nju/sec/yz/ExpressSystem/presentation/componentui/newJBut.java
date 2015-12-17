package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class newJBut extends JButton {
	Color generalcolor=new Color(139,196,213);
	Color bottomcolor=new Color(70,70,70,50);
	private boolean hover;

//	// 设置按钮无框但有下划线，写入名称和前后颜色
//	public newJBut(String text, Color fore, Color after) {
//		this.forecolor = fore;
//		this.aftercolor = after;
//		// 设为透明
//		setContentAreaFilled(false);
//		setBorderPainted(false);
//		Font font = new Font("Microsoft YaHei", Font.PLAIN, 15);
//		setFont(font);
//
//		setText("<html><u>" + text + "</u></html>");
//		setFocusPainted(false);
//		setForeground(forecolor);
//		// 设置横线
//
//		addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// forecolor=aftercolor;
//				setForeground(aftercolor);
//				setBorderPainted(false);
//				repaint();
//			}
//		});
//	}

	public newJBut(String text,Color framecolor,Color bottom) {
		super(text);
		// TODO Auto-generated constructor stub
		generalcolor=framecolor;
		bottomcolor=bottom;
		init();
	}

	public newJBut(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);

		setForeground(generalcolor);
		setFont(new Font("Microsoft YaHei",Font.PLAIN,15));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hover = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hover = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		int h = getHeight();
		int w = getWidth();
		
		float tran = 1F;
		if (!hover) {
			tran = 0.7F;
			
		}
		// 步骤2
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint p1;
		GradientPaint p2;
		if (getModel().isPressed()) {
			p1 = new GradientPaint(0, 0, new Color(120, 120, 120), 0, h - 1, new Color(70,70,70));
			p2 = new GradientPaint(0, 1, new Color(70, 70, 70, 50), 0, h - 3, new Color(155, 155, 155, 100));
		} else {
			p1 = new GradientPaint(0, 0, generalcolor, 0, h - 1, new Color(0, 0, 0));
			p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0, h - 3, new Color(0, 0, 0, 50));
		}
		// 设置透明度

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
		Shape clip = g2d.getClip();
		// 绘制整个按钮
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1, h - 1, h, h);
		g2d.clip(r2d);
		GradientPaint gp = new GradientPaint(0.0F, 0.0F, new Color(70, 70, 70, 50), 0.0F, h, new Color(70, 70, 70, 50), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
		// 鼠标移入就绘制立体效果
		if (hover) {
			RoundRectangle2D.Float r2d2 = new RoundRectangle2D.Float(5, 2, w - 10, h / 2 - 1, h / 2, h / 2);
			g2d.clip(r2d2);
			GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, new Color(120,120,120), 0.0F, h / 2, new Color(70, 70, 70, 50),
					true);
			g2d.setPaint(gp2);
			g2d.fillRect(5, 2, w - 10, h / 2);
		}
		g2d.setClip(clip);
		// 绘制边框
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, w - 1, h - 1, 1, 1);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, w - 3, h - 3, 1, 1);
		g2d.dispose();
		super.paintComponent(g);
	}

}
