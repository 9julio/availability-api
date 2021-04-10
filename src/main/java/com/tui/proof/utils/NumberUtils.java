package com.tui.proof.utils;

public class NumberUtils {

    public static Long convertStringNumberToLong(String number) {
        try {
            return number == null ? null : Long.valueOf(number);
        } catch (Exception e) {
            return null;
        }
    }

}
