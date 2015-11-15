package nju.sec.yz.ExpressSystem.client;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;

import nju.sec.yz.ExpressSystem.dataservice.datafactory.DatafactoryService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.po.DeliverPO;

public class RMIHelper {

    private static final String IP = "localhost"; //Can be read from config file
    
    private static boolean inited = false;


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
    	
    	//设置服务器超时时间
    	try {
			RMISocketFactory.setSocketFactory(new RMISocketFactory() {
				@Override
				public Socket createSocket(String host, int port) throws IOException {
					Socket socket = new Socket(host, port);
					socket.setSoTimeout(3000);
					return socket;
				}
				@Override
				public ServerSocket createServerSocket(int port) throws IOException {
					return new ServerSocket(port);
				}
			});
		} catch (IOException e) {
			System.out.println("socket");
			e.printStackTrace();
		}

    	System.out.println("client is running...");
    	String urlPrefix = "rmi://" + IP + "/";
    	DatafactoryService datafactory=(DatafactoryService) 
    									Naming.lookup(urlPrefix + "DataFactorySerializableImpl");
        DatafactoryProxy.setDatafactory(datafactory);
        System.out.println("get datafactory");
    }

}
