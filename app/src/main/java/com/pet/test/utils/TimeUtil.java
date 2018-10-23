package com.pet.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangzhengguang on 2018/9/18.
 */

public class TimeUtil {
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }
}
