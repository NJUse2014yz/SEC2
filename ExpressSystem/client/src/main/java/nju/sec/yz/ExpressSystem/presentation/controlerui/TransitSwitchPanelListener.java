package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TransitSwitchPanelListener implements MouseListener{
	private TRANSIT_CONTROL order;
	private TransitControler controler;
	public TransitSwitchPanelListener(TRANSIT_CONTROL order,TransitControler controler)
	{
		this.order=order;
		this.controler=controler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		controler.transitChangePanel(order);
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
