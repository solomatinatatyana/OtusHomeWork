package Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDateHelper {
    private Calendar calendar;
    private String currentDateFormat;

    public static final String dateTimeFormat = "dd.MM.yyyy HH:mm:ss";

    public SimpleDateHelper(String dateFormat) {
        this.calendar = Calendar.getInstance();
        this.currentDateFormat = dateFormat;
    }

    public static SimpleDateHelper getCurrentDateTime(){
        return new SimpleDateHelper(dateTimeFormat);
    }

    public String toString(){
        return new SimpleDateFormat((this.currentDateFormat)).format(this.calendar.getTime());
    }
}
