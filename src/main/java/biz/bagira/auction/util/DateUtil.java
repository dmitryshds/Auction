package biz.bagira.auction.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Dmitriy on 02.03.2017.
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     *  This method convert String to Timestamp and add current time
     * @param data = 'MM/dd/yyyy'
     * @return java.sql.Timestamp 'MM/dd/yyyy HH:mm:ss'
     */

    public static Timestamp convert(String data) {
        data = data.replaceAll("/","-").trim();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String currentTime = cal.getTime().toString();
        String[] arr = currentTime.split(" ");
        String dataTime = data + " " + arr[3];
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(simpleDateFormat2.parse(dataTime).getTime());
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        return timestamp;
    }


}
