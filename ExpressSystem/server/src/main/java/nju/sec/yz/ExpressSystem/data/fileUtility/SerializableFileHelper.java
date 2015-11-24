package nju.sec.yz.ExpressSystem.data.fileUtility;

import java.io.File;
import java.io.IOException;
/**
 * 统一管理所有文件
 * @author 周聪
 *
 */
public class SerializableFileHelper {

    public static final String DELIVER_FILE_NAME = "deliver_data";
    public static final String RECEIPT_FILE_NAME = "receipt_data";
    public static final String ACCOUNT_BOOK_FILE_NAME = "account_book_data";
    public static final String ACCOUNT_FILE_NAME = "account_data";
    public static final String IN_FILE_NAME = "in_data";
    public static final String OUT_FILE_NAME = "out_data";
    public static final String CAR_FILE_NAME = "car_data";
    public static final String DRIVER_FILE_NAME = "driver_data";
    public static final String INVENTORY_FILE_NAME = "inventory_data";
    public static final String LOG_FILE_NAME = "log_data";
    public static final String AGENCY_FILE_NAME = "agency_data";
    public static final String CONST_FILE_NAME = "const_data";
    public static final String SALARY_FILE_NAME = "salary_data";
    public static final String STAFF_FILE_NAME = "staff_data";
    public static final String USER_FILE_NAME = "user_data";
    public static final String RECEIPT_COUNTER_FILE_NAME = "receipt_counter_data";
    public static final String ORDER_FILE_NAME = "order_data";
    public static final String CITYID_FILE_NAME = "cityid_data";
    public static final String COLLECTION_RECORD_FILE_NAME="collection_record_data";
    public static final String BAR_IDS_FILE_NAME="bar_ids_data";
    
    public static File getBarIdsFile() throws IOException {
        File file = new File(BAR_IDS_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getCollectionRecordFile() throws IOException {
        File file = new File(COLLECTION_RECORD_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getCityIdFile() throws IOException {
        File file = new File(CITYID_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getDeliverFile() throws IOException {
        File file = new File(DELIVER_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getOrderFile() throws IOException {
        File file = new File(ORDER_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getAgencyFile() throws IOException {
        File file = new File(AGENCY_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getSalaryFile() throws IOException{
    	File file = new File(SALARY_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getConstFile() throws IOException {
        File file = new File(CONST_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getStaffFile() throws IOException {
        File file = new File(STAFF_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getOutFile() throws IOException{
    	File file = new File(OUT_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getCarFile() throws IOException {
        File file = new File(CAR_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getDriverFile() throws IOException {
        File file = new File(DRIVER_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getLogFile() throws IOException{
    	File file = new File(LOG_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
   
    public static File getInventoryFile() throws IOException {
        File file = new File(INVENTORY_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getReceiptFile() throws IOException {
        File file = new File(RECEIPT_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getReceiptCounterFile() throws IOException {
        File file = new File(RECEIPT_COUNTER_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getAccountBookFile() throws IOException{
    	File file = new File(ACCOUNT_BOOK_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getAccountFile() throws IOException {
        File file = new File(ACCOUNT_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }

    public static File getInFile() throws IOException {
        File file = new File(IN_FILE_NAME);
        createFileIfNotExists(file);
        return file;
    }
    
    public static File getUserFile() throws IOException {
        File file = new File(USER_FILE_NAME);
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
