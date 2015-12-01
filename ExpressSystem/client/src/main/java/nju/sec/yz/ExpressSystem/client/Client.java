package nju.sec.yz.ExpressSystem.client;

import java.util.List;

import javax.swing.JOptionPane;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Car;
import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.Driver;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;



public class Client {

    public static void main(String[] args) {
        try {
            RMIHelper.init();
           
            ClientControler control=new ClientControler();
    		control.showFrame();
        } catch (ClientInitException e) {
            e.printStackTrace();
            RMIExceptionHandler.handleRMIException();
        }

    }
}
