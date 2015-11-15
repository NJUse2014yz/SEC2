package nju.sec.yz.ExpressSystem.data.fileUtility;

import java.io.File;
import java.io.IOException;

public class SerializableFileHelper {

    public static final String DELIVER_FILE_NAME = "deliver_data";
    public static final String RECEIPT_FILE_NAME = "receipt_data";
   

    public static File getDeliverFile() throws IOException {
        File file = new File(DELIVER_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getReceiptFile() throws IOException {
        File file = new File(RECEIPT_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }


   

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
