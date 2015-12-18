package nju.sec.yz.ExpressSystem.client.rmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IPConfig {

	static final File file=new File("File/IPConfigure.txt");
	
	static String getIP(){
		String IP="localhost";//默认
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			reader.readLine();
			reader.readLine();
			//read the third line
			IP=reader.readLine();
			IP=IP.trim();//去除多余空格
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return IP;
	}
	
}
