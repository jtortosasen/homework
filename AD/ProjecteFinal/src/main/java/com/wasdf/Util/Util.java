package com.wasdf.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Date stringToDate(String date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDate = null;
        try {
            dateDate = df.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDate;
    }

    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);

    }

    public static Date parseDate(Date date){
        return stringToDate(dateToString(date));
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
