package com.tui.proof.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String HOUR_FORMAT = "HH:ss";

    public static String convertDateToDateFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static String convertDateToHourFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(HOUR_FORMAT).format(date);
    }

    public static Date convertStringDateToDateWithFormat(String date) {
        try {
            return date == null ? null : new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }


}
