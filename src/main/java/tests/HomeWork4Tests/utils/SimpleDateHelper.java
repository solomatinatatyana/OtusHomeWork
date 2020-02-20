package tests.HomeWork4Tests.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateHelper {
    private Date date;
    private static final String dateFormat = "dd.MM.yyyy_HH:MM:ss";
    public SimpleDateHelper() { }

    public static String getCurrentDateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    public static String getCurrentDateTime(String dateFormat){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }
}
