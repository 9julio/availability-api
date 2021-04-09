package com.tui.proof.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String HOUR_FORMAT = "HH:ss";

    public static String convertDateToDateFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static  String convertDateToHourFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(HOUR_FORMAT).format(date);
    }

}
