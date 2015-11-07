package nju.sec.yz.ExpressSystem.bl.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectDeepCopy {
	
	/**
	 * 复制对象
	 */
	public static Object deepCopy(Object a){
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos;
		Object b=null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(a);
			ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois=new ObjectInputStream(bis);
			b= ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
}
