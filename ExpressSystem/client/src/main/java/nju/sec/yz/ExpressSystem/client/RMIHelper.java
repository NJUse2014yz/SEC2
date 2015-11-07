package nju.sec.yz.ExpressSystem.client;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import nju.sec.yz.ExpressSystem.dataservice.datafactory.DatafactoryService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;

public class RMIHelper {

    private static final String IP = "localhost"; //Can be read from config file
    
    private static boolean inited = false;

    private static DatafactoryService datafactory;

    public synchronized static void init() throws ClientInitException {
        if (inited) {
            return;
        }
        try {
            initObjects();
            inited = true;
        } catch (Exception e) {
            throw new ClientInitException(e);
        }
    }

    private static void initObjects() throws MalformedURLException, RemoteException, NotBoundException {
        System.out.println("client is running...");
    	String urlPrefix = "rmi://" + IP + "/";
        datafactory = (DatafactoryService) Naming.lookup(urlPrefix + "DataFactorySerializableImpl");
        System.out.println("get datafactory");
        DeliverDataService deliverData=datafactory.getDeliverDataService();
        System.out.println("get deliverData");
    }

    public static DatafactoryService getDatafactory() {
        return datafactory;
    }
}
