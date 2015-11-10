package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeliverSwitchPanelListener implements MouseListener{
	private DELIVER_CONTROL order;
	private DeliverControler controler;
	public DeliverSwitchPanelListener(DELIVER_CONTROL order,DeliverControler controler)
	{
		this.order=order;
		this.controler=controler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		controler.deliverChangePanel(order);
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
