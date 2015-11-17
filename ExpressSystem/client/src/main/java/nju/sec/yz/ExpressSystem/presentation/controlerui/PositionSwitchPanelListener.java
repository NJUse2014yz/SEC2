package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PositionSwitchPanelListener implements MouseListener{
	private POSITION_CONTROL order;
	private PositionControler controler;
	public PositionSwitchPanelListener(POSITION_CONTROL order,PositionControler controler)
	{
		this.order=order;
		this.controler=controler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		controler.positionChangePanel(order);
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
