package cn.seecoder.courselearning.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String getDateString(Date date){
        return df.format(date);
    }

    public static Date toDate(String dateString) throws ParseException {
        return df.parse(dateString);
    }
}
