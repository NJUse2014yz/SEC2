package nju.sec.yz.ExpressSystem.client;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIHelper {

    private static final String IP = "localhost"; //Can be read from config file

    private static boolean inited = false;

//    private static OrderBL orderBL;

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
        String urlPrefix = "rmi://" + IP + "/";
//      orderBL = (OrderBL) Naming.lookup(urlPrefix + "order-businesslogic");
    }

   /* public static OrderBL getOrderBL() {
        return orderBL;
    }*/
}
