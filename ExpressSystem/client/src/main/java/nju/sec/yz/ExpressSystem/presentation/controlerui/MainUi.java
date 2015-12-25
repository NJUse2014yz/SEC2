package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainUi extends JFrame{
	private JPanel now;
	public final static int WIDTH=491;
	public final static int HEIGHT=551;
	private int x;
	private int y;
	private int xx;
	private int yy;
	private boolean isDraging;

	public MainUi(JPanel panel)
	{
		super();
		now=panel;
	}
	public void nextPanel(JPanel next)
	{
		remove(now);
		now=next;
		this.add(now);
		this.repaint();
	}
	public void showFrame()//crazy problem
	{
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		this.setResizable(false);
		this.setUndecorated(true);
		this.setSize(493,560);
		x=screenSize.width/2-493/2;
		y=screenSize.height/2-560/2;
		this.setLocation(x,y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(now);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				xx = e.getX();
				yy = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + e.getX() - xx, top + e.getY() - yy);
				}
			}
		});
	}
}
