package nju.sec.yz.ExpressSystem.client;

import nju.sec.yz.ExpressSystem.client.rmi.RMIExceptionHandler;
import nju.sec.yz.ExpressSystem.client.rmi.RMIHelper;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class Client {

    public static void main(String[] args) {
        try {
        	ClientControler control=new ClientControler();
        	control.showFrame();
            RMIHelper.init();
        } catch (ClientInitException e) {
            e.printStackTrace();
            RMIExceptionHandler.handleRMIException();
        }

    }
}
