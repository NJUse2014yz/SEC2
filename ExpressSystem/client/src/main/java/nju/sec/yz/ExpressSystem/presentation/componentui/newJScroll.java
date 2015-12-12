package nju.sec.yz.ExpressSystem.presentation.componentui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ScrollPaneUI;

public class newJScroll extends JScrollPane{

	protected newJScroll(Component view) {
		super(view);
		init();
	}


	public void init(){
		setUI(new myScrollUI());
	}
	
	public class myScrollUI extends ScrollPaneUI{
		
	}
}
