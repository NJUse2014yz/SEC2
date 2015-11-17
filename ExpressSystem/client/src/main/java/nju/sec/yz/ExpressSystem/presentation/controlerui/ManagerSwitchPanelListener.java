package nju.sec.yz.ExpressSystem.presentation.controlerui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManagerSwitchPanelListener implements MouseListener{
	
	private ManagerControler controler;
	private MANAGER_CONTROL order;
	
	public ManagerSwitchPanelListener(MANAGER_CONTROL order,ManagerControler controler)
	{
		this.order=order;
		this.controler=controler;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		controler.managerChangePanel(order);
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
