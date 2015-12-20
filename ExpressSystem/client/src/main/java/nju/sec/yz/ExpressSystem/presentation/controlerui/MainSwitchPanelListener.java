package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainSwitchPanelListener implements MouseListener{
	private MAIN_CONTROL order;
	private ClientControler controler;
	public MainSwitchPanelListener(MAIN_CONTROL n,ClientControler controler)
	{
		this.order=n;
		this.controler=controler;
		controler.mainChangePanel(order);
	}
	public MainSwitchPanelListener(MAIN_CONTROL n,ClientControler controler)
	{
		this.order=n;
		this.controler=controler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		controler.mainChangePanel(order);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
