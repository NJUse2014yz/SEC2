package nju.sec.yz.ExpressSystem.data.fileUtility;

import java.io.File;
import java.io.IOException;

public class SerializableFileHelper {

//    public static final String COMMODITY_FILE_NAME = "commodities";
   

    /*public static File getCommodityFile() throws IOException {
        File file = new File(getDirectory(), COMMODITY_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }*/

    


   

    private static void createFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            boolean isSuccess = file.createNewFile();
            if (!isSuccess) {
                throw new IOException("File " +
                        file.getAbsolutePath() + "can not be created");
            }
        }
    }
}
