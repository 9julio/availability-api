package com.tui.proof.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String HOUR_FORMAT = "HH:ss";

    private static final String DATE_FORMAT_FRONTEND = "yyyy-MM-dd-HH:ss";

    public static String convertDateToDateFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static String convertDateToHourFormat(Date date) {
        return date == null ? null : new SimpleDateFormat(HOUR_FORMAT).format(date);
    }

    public static Date convertFrontendDateToDateFormat(String date) {
        try {
            return date == null ? null : new SimpleDateFormat(DATE_FORMAT_FRONTEND).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringDateToDateWithFormat(String date) {
        try {
            return date == null ? null : new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static long calculateDifferenceInMinutesBetweenDates(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();

        TimeUnit time = TimeUnit.MINUTES;
        return time.convert(diff, TimeUnit.MILLISECONDS);
    }

}
